package br.com.raphaelinacio.domain.pai;


import br.com.raphaelinacio.domain.pai.Email;
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