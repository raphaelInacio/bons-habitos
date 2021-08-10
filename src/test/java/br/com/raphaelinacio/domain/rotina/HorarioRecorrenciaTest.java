package br.com.raphaelinacio.domain.rotina;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class HorarioRecorrenciaTest {

    @Test
    void naoDevePermitirCriarHorarioInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new HorarioRecorrencia(null));
    }

    @Test
    void devePermitirCriarHorario() {
        var horario = new HorarioRecorrencia(LocalTime.now());
        Assertions.assertNotNull(horario);
    }
}