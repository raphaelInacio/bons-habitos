package br.com.raphaelinacio.application;

import br.com.raphaelinacio.Application;
import br.com.raphaelinacio.core.DataBuilder;
import br.com.raphaelinacio.core.domain.pai.CadastroPaiDTO;
import br.com.raphaelinacio.core.domain.pai.Pai;
import br.com.raphaelinacio.core.domain.pai.PaiDTO;
import br.com.raphaelinacio.core.domain.rotina.RotinaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class PaiControllerTest extends DataBuilder {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    @Order(1)
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
    @Order(2)
    void deveCriarUmaNovaRotina() throws Exception {
        RotinaDTO rotinaDTO = criarRotinaDTO();
        mvc.perform(MockMvcRequestBuilders.post("/v1/pais/" + email.getEndereco() + "/rotinas")
                .content(mapper.writeValueAsString(rotinaDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isCreated())
                .andDo(print());
    }

    @Test
    @Order(3)
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
    @Order(4)
    void deveRegistrarParticipacaoEmUmaRotina() throws Exception {
        UUID idRotina = UUID.randomUUID();

        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/v1/pais/" + email.getEndereco())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk())
                .andDo(print());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        PaiDTO response = mapper.readValue(contentAsString, PaiDTO.class);

        RotinaDTO rotinaDTO = response.getRotinas().stream().findFirst().get();

        mvc.perform(MockMvcRequestBuilders.post("/v1/pais/" + email.getEndereco() + "/rotinas/" + idRotina)
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
    void deveRetornarErroQuandoEmailNaoCadastradoForEnviado() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/v1/pais/raphael.inacio@gmail.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isNoContent())
                .andDo(print());
    }


}
