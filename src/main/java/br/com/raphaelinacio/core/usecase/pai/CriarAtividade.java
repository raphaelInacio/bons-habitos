package br.com.raphaelinacio.core.usecase.pai;

import br.com.raphaelinacio.core.domain.pai.*;
import br.com.raphaelinacio.core.domain.rotina.Atividade;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class CriarAtividade {
    private final PaiRepository paiRepository;

    public CriarAtividade(PaiRepository paiRepository) {
        this.paiRepository = paiRepository;
    }

    void executar(String enderecoEmailPai, String nomeFilho, UUID codigoAtividade) {
//        Pai pai = paiRepository.buscarPaiPorEmail(enderecoEmailPai);
//
//        Atividade atividade = atividadeRepository.buscarAtividade(codigoAtividade);
//
//        Filho meuFilho = pai.meusFilhos()
//                .stream()
//                .filter(filho -> Objects.equals(filho.getNome(), nomeFilho))
//                .findFirst()
//                .orElseThrow(() -> {
//                    throw new IllegalArgumentException("");
//                });
//
//        pai.criarAtividadeParaMeuFilho(meuFilho, atividade);
//
//        paiRepository.
    }
}