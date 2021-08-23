package br.com.raphaelinacio.core.usecase.pai;

import br.com.raphaelinacio.core.DataBuilder;
import br.com.raphaelinacio.core.domain.pai.*;
import br.com.raphaelinacio.core.domain.rotina.AtividadeNaoCadastradaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class IncluirAtividadeParaMeuFilhoTest extends DataBuilder {

    IncluirAtividadeParaMeuFilho incluirAtividadeParaMeuFilho = new IncluirAtividadeParaMeuFilho(paiRepository, atividadeRepository);

    @BeforeEach
    void setup() {
        iniciarEscopoGlobal();
    }

    @Test
    void deveIncluirUmaAtividadeJaExistenteParaUmFilho() throws PaiNaoCadastradoException, AtividadeNaoCadastradaException {
        incluirAtividadeParaMeuFilho.executar(email.getEndereco(), atividadeGlobal.getCodigo());
    }

    @Test
    void naoDevePermitirIncluirAtividadeParaUmPaiNaoCadastrado() {
        Assertions.assertThrows(PaiNaoCadastradoException.class,
                () -> incluirAtividadeParaMeuFilho.executar("emailInvalido@gmail.com", UUID.randomUUID()));
    }

    @Test
    void naoDevePermitirInclusaoDeAtividadeNaoCadastrada() {
        Assertions.assertThrows(AtividadeNaoCadastradaException.class,
                () -> incluirAtividadeParaMeuFilho.executar(email.getEndereco(), UUID.randomUUID()));
    }
}