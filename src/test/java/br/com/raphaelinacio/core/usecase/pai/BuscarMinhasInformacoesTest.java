package br.com.raphaelinacio.core.usecase.pai;

import br.com.raphaelinacio.core.DataBuilder;
import br.com.raphaelinacio.core.domain.pai.dto.PaiDTO;
import br.com.raphaelinacio.core.domain.pai.repository.PaiNaoCadastradoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BuscarMinhasInformacoesTest extends DataBuilder {
    BuscarMinhasInformacoes buscarMinhasInformacoes = new BuscarMinhasInformacoes(paiRepository);

    @BeforeEach
    public void setup() {
        iniciarEscopoGlobal();
    }

    @Test
    void naoDevePermitirRecuperarPaiNaoCadastrado() throws PaiNaoCadastradoException {
        Assertions.assertThrows(PaiNaoCadastradoException.class, () -> buscarMinhasInformacoes.executar("email@gmail.com"));
    }

    @Test
    void deveRecuperarAsInformacoesDeUmPai() throws PaiNaoCadastradoException {
        PaiDTO paiDTO = buscarMinhasInformacoes.executar(paiGlobal.getEmail().getEndereco());
        Assertions.assertNotNull(paiDTO);
    }
}