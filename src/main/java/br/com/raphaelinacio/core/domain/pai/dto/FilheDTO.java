package br.com.raphaelinacio.core.domain.pai.dto;

import br.com.raphaelinacio.core.domain.pai.Filho;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class FilheDTO {

    private String nome;
    private LocalDate dataDeNascimento;

    public static List<FilheDTO> converter(List<Filho> meusFilhos) {
        return meusFilhos.stream().map(FilheDTO::new).collect(Collectors.toList());
    }

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
