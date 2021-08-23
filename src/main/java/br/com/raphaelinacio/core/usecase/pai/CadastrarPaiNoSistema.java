package br.com.raphaelinacio.core.usecase.pai;

import br.com.raphaelinacio.core.domain.pai.*;

import java.time.LocalDate;

public class CadastrarPaiNoSistema {

    private final PaiRepository paiRepository;

    public CadastrarPaiNoSistema(PaiRepository paiRepository) {
        this.paiRepository = paiRepository;
    }

    public void executar(String nomeDoPai, String enderecoEmailPai, String nomeFilho, LocalDate dataNascimentoFilho)
            throws CadastroPaiException, PaiNaoCadastradoException {

        var jaExisteCadastro = paiRepository.verificarCadastroDeEmail(new Email(enderecoEmailPai));

        if (jaExisteCadastro) {
            throw new CadastroPaiException("Já existe um cadastro de PAI para esse e-mail");
        }

        var pai = new Pai(nomeDoPai, new Email(enderecoEmailPai));

        pai.incluirFilho(new Filho(nomeFilho, dataNascimentoFilho));

        paiRepository.cadastrarPai(pai);
    }
}
