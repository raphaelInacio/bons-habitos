package br.com.raphaelinacio.core.domain.rotina;

import br.com.raphaelinacio.core.domain.pai.Pai;

import java.util.UUID;

public interface AtividadeRepository {
    Atividade buscarAtividade(UUID codigoAtividade) throws AtividadeNaoCadastradaException;

    void criarAtividade(Pai pai, Atividade atividade);

    void associarAtividade(Pai pai, Atividade atividade);
}
