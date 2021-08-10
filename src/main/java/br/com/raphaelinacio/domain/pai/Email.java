package br.com.raphaelinacio.domain.pai;

public class Email {
    private String endereco;

    public Email(String endereco) {
        if (endereco == null || !endereco.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            throw new IllegalArgumentException("Email invalido informado");
        }
        this.endereco = endereco;
    }
}
