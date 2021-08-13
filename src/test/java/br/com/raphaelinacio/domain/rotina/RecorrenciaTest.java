package br.com.raphaelinacio.domain.rotina;

import br.com.raphaelinacio.domain.pai.DataBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RecorrenciaTest extends DataBuilder {

    @Test
    public void deveCriarUmaRecorrencia() {
        criarRecorrencia();
    }

    @Test
    public void naoDevePermitirCriaRecorrenciaComDadosInvalidos() {
        assertThrows(IllegalArgumentException.class, () -> new Recorrencia(null, null, null));
        assertThrows(IllegalArgumentException.class, () -> new Recorrencia(TipoRecorrenciaEnum.DIARIA, null, LocalDate.now()));
        assertThrows(IllegalArgumentException.class, () -> new Recorrencia(null, new HorarioRecorrencia(LocalTime.now()), LocalDate.now()));
        assertThrows(IllegalArgumentException.class, () -> new Recorrencia(TipoRecorrenciaEnum.DIARIA, new HorarioRecorrencia(LocalTime.now()), null));
    }

}