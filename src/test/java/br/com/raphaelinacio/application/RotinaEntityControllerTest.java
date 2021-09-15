package br.com.raphaelinacio.application;

import br.com.raphaelinacio.Application;
import br.com.raphaelinacio.core.DataBuilder;
import br.com.raphaelinacio.core.domain.rotina.Rotina;
import br.com.raphaelinacio.core.domain.rotina.repository.RotinaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
class RotinaEntityControllerTest extends DataBuilder {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    @Qualifier("rotinaRepositoryDataStoreImpl")
    private RotinaRepository rotinaRepository;

    @Test
    void deveCriarUmaRotinaDeSistema() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/v1/rotinas")
                .content(mapper.writeValueAsString(criarRotinaDTO()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isCreated())
                .andDo(print());
    }

    @Test
    void deveListarTodasRotinasDeSistema() throws Exception {
        rotinaRepository.criarRotinaDeSistema(criarRotinaSistema());
        mvc.perform(MockMvcRequestBuilders.get("/v1/rotinas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk())
                .andDo(print());
    }

    @Test
    void deveRecuperarUmaRotinaPorCodigo() throws Exception {
        Rotina rotina = criarRotinaSistema();
        rotinaRepository.criarRotinaDeSistema(rotina);
        mvc.perform(MockMvcRequestBuilders.get("/v1/rotinas/" + rotina.getCodigo())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk())
                .andDo(print());
    }

    @Test
    void deveRetornar422QuandoUmCodigoDeRotinaInvalidoForEnviado() throws Exception {
        Rotina rotina = criarRotinaSistema();
        rotinaRepository.criarRotinaDeSistema(rotina);
        mvc.perform(MockMvcRequestBuilders.get("/v1/rotinas/" + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isNoContent())
                .andDo(print());
    }
}