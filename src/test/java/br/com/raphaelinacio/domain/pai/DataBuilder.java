package br.com.raphaelinacio.domain.pai;

import br.com.raphaelinacio.domain.rotina.Habito;

import java.time.LocalDate;

public class DataBuilder {

    public Pai criarPai() {
        Pai pai = new Pai("Raphael Inacio", new Email("contato.raphaelinacio@gmail.com"));
        return pai;
    }

    public Filho criarFilho() {
        return new Filho("Raphael Silva Dias", LocalDate.of(2014, 7, 3));
    }

    public Pai criarPaiComFilho() {
        Pai pai = criarPai();
        pai.incluirFilho(criarFilho());
        return pai;
    }

    public Habito criarHabito() {
        return new Habito(
                "Novo Habito",
                "Cadastrando um novo habito",
                "Este habito serve para testar a aplicação");
    }

}
