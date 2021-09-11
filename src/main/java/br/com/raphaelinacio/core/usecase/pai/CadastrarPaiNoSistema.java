package br.com.raphaelinacio.core.usecase.pai;

import br.com.raphaelinacio.core.domain.pai.*;
import br.com.raphaelinacio.core.domain.pai.dto.CadastroPaiDTO;
import br.com.raphaelinacio.core.domain.pai.exception.PaiRepository;
import br.com.raphaelinacio.core.domain.pai.repository.CadastroPaiException;
import br.com.raphaelinacio.core.domain.pai.repository.PaiNaoCadastradoException;

public class CadastrarPaiNoSistema {

    private final PaiRepository paiRepository;

    public CadastrarPaiNoSistema(PaiRepository paiRepository) {
        this.paiRepository = paiRepository;
    }

    public void executar(CadastroPaiDTO cadastroPaiDTO) throws CadastroPaiException, PaiNaoCadastradoException {

        var jaExisteCadastro = paiRepository.verificarCadastroDeEmail(new Email(cadastroPaiDTO.getEnderecoEmailPai()));

        if (jaExisteCadastro) {
            throw new CadastroPaiException("Já existe um cadastro de PAI para esse e-mail");
        }

        var pai = new Pai(cadastroPaiDTO.getNomeDoPai(), new Email(cadastroPaiDTO.getEnderecoEmailPai()));

        pai.incluirFilho(new Filho(cadastroPaiDTO.getNomeFilho(), cadastroPaiDTO.getDataNascimentoFilho()));

        paiRepository.cadastrarPai(pai);
    }
}
