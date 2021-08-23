package br.com.raphaelinacio.core.domain.rotina;

import br.com.raphaelinacio.core.DataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AtividadeTest extends DataBuilder {

    @Test
    void naoDeveCriarHabitoComDadosInvalidos() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Atividade("", "Titulo", "Descricao"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Atividade(null, "Titulo", "Descricao"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Atividade("Nome", "", "Descricao"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Atividade("Nome", null, "Descricao"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Atividade("", "Titulo", null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Atividade("", "Titulo", ""));
    }

    @Test
    void deveCriarUmaAtividade() {
        criarAtividadeSistema();
    }

}