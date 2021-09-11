package br.com.raphaelinacio.core.usecase.pai;

import br.com.raphaelinacio.core.DataBuilder;
import br.com.raphaelinacio.core.domain.pai.repository.PaiNaoCadastradoException;
import br.com.raphaelinacio.core.domain.rotina.exception.RotinaNaoCadastradaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class RegistrarParticipacaoTest extends DataBuilder {

    RegistrarParticipacao registrarParticipacao = new RegistrarParticipacao(paiRepository, rotinaRepository);

    @BeforeEach
    public void setup() {
        iniciarEscopoGlobal();
    }

    @Test
    void naoDevePermitiRegistrosNoMesmoDia() throws RotinaNaoCadastradaException, PaiNaoCadastradoException {
        registrarParticipacao.executar(paiGlobal.getEmail().getEndereco(), rotinaGlobal.getCodigo());
        Assertions.assertThrows(IllegalStateException.class, () -> registrarParticipacao.executar(paiGlobal.getEmail().getEndereco(), rotinaGlobal.getCodigo()));
    }

    @Test
    void naoDevePermitiRegistrosDeRotinasNaoCadastradas() {
        Assertions.assertThrows(RotinaNaoCadastradaException.class, () -> registrarParticipacao.executar(paiGlobal.getEmail().getEndereco(), UUID.randomUUID()));
    }

    @Test
    void devePermitirRegistrarParticipacao() throws RotinaNaoCadastradaException, PaiNaoCadastradoException {
        registrarParticipacao.executar(paiGlobal.getEmail().getEndereco(), rotinaGlobal.getCodigo());
    }

}