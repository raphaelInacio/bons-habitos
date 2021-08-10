package br.com.raphaelinacio.domain.pai;

import br.com.raphaelinacio.domain.pai.Filho;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FilhoTest {

    @Test
    public void naoDevePermitirCadastrarFilhoComNomeVazioOuNulo() {
        assertThrows(IllegalArgumentException.class, () -> new Filho("", LocalDate.now()));
        assertThrows(IllegalArgumentException.class, () -> new Filho(null, LocalDate.now()));
    }

    @Test
    public void naoDevePermitirCadastrarFilhoSemDataDeNascimento() {
        assertThrows(IllegalArgumentException.class, () -> new Filho("Rafal Silva Dias", null));
    }
}