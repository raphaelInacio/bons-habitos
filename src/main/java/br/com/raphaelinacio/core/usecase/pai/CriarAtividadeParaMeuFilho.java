package br.com.raphaelinacio.core.usecase.pai;

import br.com.raphaelinacio.core.domain.pai.Email;
import br.com.raphaelinacio.core.domain.pai.Pai;
import br.com.raphaelinacio.core.domain.pai.PaiNaoCadastradoException;
import br.com.raphaelinacio.core.domain.pai.PaiRepository;
import br.com.raphaelinacio.core.domain.rotina.Atividade;
import br.com.raphaelinacio.core.domain.rotina.AtividadeDTO;
import br.com.raphaelinacio.core.domain.rotina.AtividadeException;
import br.com.raphaelinacio.core.domain.rotina.AtividadeRepository;

public class CriarAtividadeParaMeuFilho {

    private final PaiRepository paiRepository;
    private final AtividadeRepository atividadeRepository;

    public CriarAtividadeParaMeuFilho(PaiRepository paiRepository, AtividadeRepository atividadeRepository) {
        this.paiRepository = paiRepository;
        this.atividadeRepository = atividadeRepository;
    }

    void executar(String enderecoEmailPai, AtividadeDTO atividadeDTO) throws AtividadeException, PaiNaoCadastradoException {
        Email email = new Email(enderecoEmailPai);

        Pai pai = paiRepository.buscarPaiPorEmail(email);

        Atividade novaAtividade = atividadeDTO.converterParaDominioAtividadePai();

        atividadeRepository.criarAtividade(pai, novaAtividade);
    }

}