package br.com.raphaelinacio.application;

import br.com.raphaelinacio.core.DataBuilder;
import br.com.raphaelinacio.core.domain.pai.CadastroPaiDTO;
import br.com.raphaelinacio.Application;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class PaiControllerTest extends DataBuilder {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void deveCadasrtarUmPaiNoSistema() throws Exception {

        CadastroPaiDTO cadastroPaiDTO = new CadastroPaiDTO("Raphael Inacio", email.getEndereco(), "Raphinha", LocalDate.now());

        mvc.perform(MockMvcRequestBuilders.post("/v1/pais")
                .content(mapper.writeValueAsString(cadastroPaiDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isCreated())
                .andDo(print());
    }
}
