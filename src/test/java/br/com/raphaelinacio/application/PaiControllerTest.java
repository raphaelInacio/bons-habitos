package br.com.raphaelinacio.application;

import br.com.raphaelinacio.core.DataBuilder;
import br.com.raphaelinacio.core.domain.pai.CadastroPaiDTO;
import br.com.raphaelinacio.Application;
import br.com.raphaelinacio.core.domain.pai.Pai;
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
        Pai pai = criarPai();
        CadastroPaiDTO cadastroPaiDTO = new CadastroPaiDTO(pai.getNome(), "raphael@gmail.com", "Raphinha", LocalDate.now());

        mvc.perform(MockMvcRequestBuilders.post("/v1/pais")
                .content(mapper.writeValueAsString(cadastroPaiDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isCreated())
                .andDo(print());
    }

    @Test
    void naoDevePermitirCadastroDePaisComMesmoEmail() throws Exception {
        Pai pai = criarPai();
        CadastroPaiDTO cadastroPaiDTO = new CadastroPaiDTO(pai.getNome(), pai.getEmail().getEndereco(), "Raphinha", LocalDate.now());

        mvc.perform(MockMvcRequestBuilders.post("/v1/pais")
                .content(mapper.writeValueAsString(cadastroPaiDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isCreated())
                .andDo(print());

        mvc.perform(MockMvcRequestBuilders.post("/v1/pais")
                .content(mapper.writeValueAsString(cadastroPaiDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is4xxClientError())
                .andDo(print());
    }

    @Test
    void deveRecuperarOsDadosDeUmPai() throws Exception {
        Pai pai = criarPai();
        mvc.perform(MockMvcRequestBuilders.get("/v1/pais/" + email.getEndereco())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("email").value(email.getEndereco()))
                .andExpect(MockMvcResultMatchers.jsonPath("nome").value(pai.getNome()))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk())
                .andDo(print());
    }

    @Test
    void deveRetornarErroQuandoEmailNaoCadastradoForEnviado() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/v1/pais/raphael.inacio@gmail.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isNoContent())
                .andDo(print());
    }
}
