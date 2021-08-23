package br.com.raphaelinacio.core.usecase.pai;

import br.com.raphaelinacio.core.DataBuilder;
import br.com.raphaelinacio.core.domain.pai.PaiNaoCadastradoException;
import br.com.raphaelinacio.core.domain.rotina.AtividadeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CriarAtividadeParaMeuFilhoTest extends DataBuilder {

    public CriarAtividadeParaMeuFilho criarAtividadeParaMeuFilho = new CriarAtividadeParaMeuFilho(paiRepository, atividadeRepository);

    @BeforeEach
    void setup() {
        iniciarEscopoGlobal();
    }

    @Test
    void deveCriarUmaAtividadeParaMeuFilho() throws AtividadeException, PaiNaoCadastradoException {
        criarAtividadeParaMeuFilho.executar(paiGlobal.getEmail().getEndereco(), criarAtividadeDTO());
    }

}