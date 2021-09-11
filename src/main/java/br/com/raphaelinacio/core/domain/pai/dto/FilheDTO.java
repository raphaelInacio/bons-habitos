package br.com.raphaelinacio.core.domain.pai.dto;

import br.com.raphaelinacio.core.domain.pai.Filho;

import java.time.LocalDate;

public class FilheDTO {
    private String nome;
    private LocalDate dataDeNascimento;

    public String getNome() {
        return nome;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public FilheDTO(Filho filho) {
        this.nome = filho.getNome();
        this.dataDeNascimento = filho.getDataDeNascimento();
    }

    public FilheDTO() {
    }
}
