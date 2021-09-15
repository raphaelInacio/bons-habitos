package br.com.raphaelinacio.application;

import br.com.raphaelinacio.Application;
import br.com.raphaelinacio.core.DataBuilder;
import br.com.raphaelinacio.core.domain.pai.dto.CadastroPaiDTO;
import br.com.raphaelinacio.core.domain.pai.Pai;
import br.com.raphaelinacio.core.domain.pai.exception.PaiRepository;
import br.com.raphaelinacio.core.domain.rotina.dto.RotinaDTO;
import br.com.raphaelinacio.core.domain.rotina.repository.RotinaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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

    @Autowired
    @Qualifier("dataStoreRepository")
    private PaiRepository paiRepository;

    @Autowired
    private RotinaRepository rotinaRepository;

    @BeforeEach
    public void setup() {
        Pai pai = criarPai();
        paiRepository.removerCadastro(pai.getEmail());
    }

    private Pai criarPaiNoBanco() {
        Pai pai = criarPaiComFilho();
        paiRepository.cadastrarPai(pai);
        return pai;
    }

    private Pai criarPaiComRotinaNoBanco() {
        Pai pai = criarPaiComRotina();
        paiRepository.cadastrarPai(pai);
        rotinaRepository.criarRotina(pai, pai.minhaRotina().stream().findFirst().get());
        return pai;
    }

    @Test
    void deveCadastrarUmPaiNoSistema() throws Exception {
        Pai pai = criarPai();
        CadastroPaiDTO cadastroPaiDTO = new CadastroPaiDTO(pai.getNome(), pai.getEmail().getEndereco(), "Raphinha", LocalDate.now());
        mvc.perform(MockMvcRequestBuilders.post("/v1/pais")
                .content(mapper.writeValueAsString(cadastroPaiDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isCreated())
                .andDo(print());
    }

    @Test
    void deveCriarUmaNovaRotina() throws Exception {
        Pai pai = criarPaiNoBanco();
        RotinaDTO rotinaDTO = criarRotinaDTO();
        mvc.perform(MockMvcRequestBuilders.post("/v1/pais/" + pai.getEmail().getEndereco() + "/rotinas")
                .content(mapper.writeValueAsString(rotinaDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isCreated())
                .andDo(print());
    }

    @Test
    void deveRecuperarOsDadosDeUmPai() throws Exception {
        Pai pai = criarPaiNoBanco();
        mvc.perform(MockMvcRequestBuilders.get("/v1/pais/" + pai.getEmail().getEndereco())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("email").value(pai.getEmail().getEndereco()))
                .andExpect(MockMvcResultMatchers.jsonPath("nome").value(pai.getNome()))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk())
                .andDo(print());
    }

    @Test
    void deveRegistrarParticipacaoEmUmaRotina() throws Exception {
        Pai pai = criarPaiComRotinaNoBanco();
        UUID codigo = pai.minhaRotina().stream().findFirst().get().getCodigo();
        mvc.perform(MockMvcRequestBuilders.post("/v1/pais/" + pai.getEmail().getEndereco() + "/rotinas/" + codigo)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isCreated())
                .andDo(print());
    }

    @Test
    void deveRetornarAsRotinasDeUmPai() throws Exception {
        Pai pai = criarPaiComRotinaNoBanco();
        mvc.perform(MockMvcRequestBuilders.get("/v1/pais/" + pai.getEmail().getEndereco() + "/rotinas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk())
                .andDo(print());
    }

    @Test
    void naoDevePermitirCadastroDePaisComMesmoEmail() throws Exception {
        Pai pai = criarPaiNoBanco();
        CadastroPaiDTO cadastroPaiDTO = new CadastroPaiDTO(pai.getNome(), pai.getEmail().getEndereco(), "Raphinha", LocalDate.now());
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
