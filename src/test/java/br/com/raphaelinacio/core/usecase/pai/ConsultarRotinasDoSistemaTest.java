package br.com.raphaelinacio.core.usecase.pai;

import br.com.raphaelinacio.core.DataBuilder;
import br.com.raphaelinacio.core.domain.rotina.dto.RotinaDTO;
import br.com.raphaelinacio.core.usecase.rotina.ConsultarRotinasDoSistema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class ConsultarRotinasDoSistemaTest extends DataBuilder {

    ConsultarRotinasDoSistema consultarRotinasDoSistema = new ConsultarRotinasDoSistema(rotinaRepository);

    @BeforeEach
    public void setup() {
        iniciarEscopoGlobal();
    }

    @Test
    void deveRecuperarRotinasDeSistema() {
        List<RotinaDTO> rotinaDTOS = consultarRotinasDoSistema.executar();
        Assertions.assertNotNull(rotinaDTOS);
    }

}


