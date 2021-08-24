package br.com.raphaelinacio.core.usecase.pai;

import br.com.raphaelinacio.core.domain.pai.*;
import br.com.raphaelinacio.core.domain.rotina.Atividade;
import br.com.raphaelinacio.core.domain.rotina.AtividadeNaoCadastradaException;
import br.com.raphaelinacio.core.domain.rotina.AtividadeRepository;

import java.util.UUID;

public class IncluirAtividadeParaMeuFilho {

    private final PaiRepository paiRepository;
    private final AtividadeRepository atividadeRepository;

    public IncluirAtividadeParaMeuFilho(PaiRepository paiRepository, AtividadeRepository atividadeRepository) {
        this.paiRepository = paiRepository;
        this.atividadeRepository = atividadeRepository;
    }

    void executar(String enderecoEmailPai, UUID codigoAtividade) throws AtividadeNaoCadastradaException, PaiNaoCadastradoException {
        Email email = new Email(enderecoEmailPai);

        Pai pai = paiRepository.buscarPaiPorEmail(email);

        Atividade atividade = atividadeRepository.buscarAtividade(codigoAtividade);

        atividadeRepository.associarAtividade(pai, atividade);
    }

}