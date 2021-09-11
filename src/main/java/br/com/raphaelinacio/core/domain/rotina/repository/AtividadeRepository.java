package br.com.raphaelinacio.core.domain.rotina.repository;

import br.com.raphaelinacio.core.domain.pai.Pai;
import br.com.raphaelinacio.core.domain.rotina.Atividade;
import br.com.raphaelinacio.core.domain.rotina.exception.AtividadeNaoCadastradaException;

import java.util.UUID;

public interface AtividadeRepository {
    Atividade buscarAtividade(UUID codigoAtividade) throws AtividadeNaoCadastradaException;

    void criarAtividade(Pai pai, Atividade atividade);

    void associarAtividade(Pai pai, Atividade atividade);
}
