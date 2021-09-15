package br.com.raphaelinacio.core.domain.rotina;

import br.com.raphaelinacio.core.DataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RotinaEntityTest extends DataBuilder {

    @Test
    void deveCriarUmaRotinaDeSistema() {
        var rotina = criarRotinaSistema();
        assertNotNull(rotina);
        assertEquals(TipoRotina.SISTEMA, rotina.getTipoRotina());
    }

    @Test
    void deveCriarUmaRotinaPai() {
        var rotina = criarRotinaPai();
        assertNotNull(rotina);
        assertEquals(TipoRotina.PAI, rotina.getTipoRotina());
    }

    @Test
    void naoDevePermitirQuePaisCriemRotinasDeSistema() {
        assertThrows(IllegalArgumentException.class, () -> new Rotina(criarRecorrencia(), criarAtividadeSistema(), TipoRotina.SISTEMA));
    }

    @Test
    void naoDevePermitirRegistrarDuasNoMesmoDiaMesmaRotina() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            var rotina = criarRotinaSistema();
            rotina.registrarParticipacaoDiaria();
            rotina.registrarParticipacaoDiaria();
        });
    }

    @Test
    void deveVerHistoricoDeParticipacoes() {
        var rotina = criarRotinaSistema();
        rotina.registrarParticipacaoDiaria();
        Assertions.assertEquals(1, rotina.verHistoricoDeParticipacoes().size());
    }
}