package br.com.raphaelinacio.core.domain.pai;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EmailTest {

    @Test
    public void naoDeveCadastrarEmailInvalido() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Email(""));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Email("emailinvalido.com"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Email(null));
    }

}