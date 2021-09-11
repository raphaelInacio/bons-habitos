package br.com.raphaelinacio.infra.database;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Filho {
    private String nome;
    private LocalDate dataNacimento;
}
