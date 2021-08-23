package br.com.raphaelinacio.core.domain.rotina;

import br.com.raphaelinacio.core.DataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RotinaTest extends DataBuilder {

    @Test
    void deveCriarUmaRotina() {
        var rotina = criarRotina();
        assertNotNull(rotina);
    }

    @Test
    void naoDevePermitirRegistrarDuasNoMesmoDiaMesmaRotina() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            var rotina = criarRotina();
            rotina.registrarParticipacaoDiaria();
            rotina.registrarParticipacaoDiaria();
        });
    }

    @Test
    void deveVerHistoricoDeParticipacoes() {
        var rotina = criarRotina();
        rotina.registrarParticipacaoDiaria();
        Assertions.assertEquals(1, rotina.verHistoricoDeParticipacoes().size());
    }
}