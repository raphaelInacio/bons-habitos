package br.com.raphaelinacio.core.usecase.pai;

import br.com.raphaelinacio.core.DataBuilder;
import br.com.raphaelinacio.core.domain.pai.Email;
import br.com.raphaelinacio.core.domain.pai.Pai;
import br.com.raphaelinacio.core.domain.pai.PaiNaoCadastradoException;
import br.com.raphaelinacio.core.domain.rotina.RotinaDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class ConsultarMinhasRotinasTest extends DataBuilder {

    ConsultarMinhasRotinas consultarMinhasRotinas = new ConsultarMinhasRotinas(paiRepository, rotinaRepository);

    @BeforeEach
    public void setup() {
        iniciarEscopoGlobal();
    }

    @Test
    void deveRecuperarAsRotinasDeUmPai() throws PaiNaoCadastradoException {
        List<RotinaDTO> minhasRotinas = consultarMinhasRotinas.executar(email.getEndereco());
        Assertions.assertTrue(!minhasRotinas.isEmpty());
    }


    @Test
    void deveRetornarListaVaziaQuandoNaoHouverRotinaCadastrada() throws PaiNaoCadastradoException {
        Pai pai = new Pai("Pai teste 2", new Email("emailreste@gmail.com"));
        paiRepository.cadastrarPai(pai);
        List<RotinaDTO> minhasRotinas = consultarMinhasRotinas.executar(pai.getEmail().getEndereco());
        Assertions.assertTrue(minhasRotinas.isEmpty());
    }

}