package br.com.raphaelinacio.domain.rotina;

import java.time.LocalDateTime;

public class Historico {

    LocalDateTime momento;

    public LocalDateTime getMomento() {
        return momento;
    }

    public Historico() {
        this.momento = LocalDateTime.now();
    }
}
