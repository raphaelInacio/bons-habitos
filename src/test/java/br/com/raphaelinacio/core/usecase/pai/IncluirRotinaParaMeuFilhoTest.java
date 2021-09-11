package br.com.raphaelinacio.core.usecase.pai;

import br.com.raphaelinacio.core.DataBuilder;
import br.com.raphaelinacio.core.domain.pai.repository.PaiNaoCadastradoException;
import br.com.raphaelinacio.core.domain.rotina.exception.RotinaNaoCadastradaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class IncluirRotinaParaMeuFilhoTest extends DataBuilder {

    IncluirRotinaParaMeuFilho incluirRotinaParaMeuFilho = new IncluirRotinaParaMeuFilho(paiRepository, rotinaRepository);

    @BeforeEach
    void setup() {
        iniciarEscopoGlobal();
    }

    @Test
    void deveIncluirUmaRotinaJaExistenteParaUmFilho() throws PaiNaoCadastradoException, RotinaNaoCadastradaException {
        incluirRotinaParaMeuFilho.executar(email.getEndereco(), rotinaGlobal.getCodigo());
    }

    @Test
    void naoDevePermitirIncluirAtividadeParaUmPaiNaoCadastrado() {
        Assertions.assertThrows(PaiNaoCadastradoException.class,
                () -> incluirRotinaParaMeuFilho.executar("emailInvalido@gmail.com", UUID.randomUUID()));
    }

    @Test
    void naoDevePermitirInclusaoDeRotinaNaoCadastrada() {
        Assertions.assertThrows(RotinaNaoCadastradaException.class,
                () -> incluirRotinaParaMeuFilho.executar(email.getEndereco(), UUID.randomUUID()));
    }
}