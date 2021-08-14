package br.com.raphaelinacio.core.usecase.pai;

import br.com.raphaelinacio.core.domain.pai.DataBuilder;
import br.com.raphaelinacio.core.domain.pai.Pai;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class CriarAtividadeTest extends DataBuilder {
    CriarAtividade criarAtividade = new CriarAtividade(paiRepository);

    @Test
    void deveCriarUmaAtividade() {
        criarAtividade.executar(email.getEndereco(), nomeFilho, UUID.randomUUID());
    }
}