package br.com.raphaelinacio.domain.rotina;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HabitoTest {

    @Test
    void naoDeveCriarHabitoComDadosInvalidos() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Habito("", "Titulo", "Descricao"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Habito(null, "Titulo", "Descricao"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Habito("Nome", "", "Descricao"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Habito("Nome", null, "Descricao"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Habito("", "Titulo", null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Habito("", "Titulo", ""));
    }

}