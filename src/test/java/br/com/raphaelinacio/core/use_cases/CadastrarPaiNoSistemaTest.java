package br.com.raphaelinacio.core.use_cases;

import br.com.raphaelinacio.core.domain.pai.CadastroPaiException;
import br.com.raphaelinacio.core.usecase.pai.CadastrarPaiNoSistema;
import br.com.raphaelinacio.core.domain.pai.DataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class CadastrarPaiNoSistemaTest extends DataBuilder {

    private CadastrarPaiNoSistema cadastrarPaiNoSistema = new CadastrarPaiNoSistema(paiRepository);

    @Test
    void deveCadastrarUmPaiNoSistemaCorretamente() throws CadastroPaiException {
        cadastrarPaiNoSistema.executar("Raphael Inacio", "contato.raphaelinacio@gmail.com", "Raphael Silva Dias", LocalDate.now());
    }

    @Test
    void naoDevePermitirCadastroDePaisComMesmoEmail() {
        Assertions.assertThrows(CadastroPaiException.class, () -> {
            cadastrarPaiNoSistema.executar("Raphael Inacio", "contato.raphaelinacio@gmail.com", "Raphael Silva Dias", LocalDate.now());
            cadastrarPaiNoSistema.executar("Raphael Inacio", "contato.raphaelinacio@gmail.com", "Raphael Silva Dias", LocalDate.now());
        });

    }
}