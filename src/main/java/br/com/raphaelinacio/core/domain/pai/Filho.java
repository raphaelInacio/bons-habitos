package br.com.raphaelinacio.core.domain.pai;

import java.time.LocalDate;
import java.util.Objects;

public class Filho {
    private String nome;
    private LocalDate dataDeNascimento;

    public Filho(String nome, LocalDate dataDeNascimento) {
        if (nome == null || nome == "") {
            throw new IllegalArgumentException("Nome invalido informado");
        }

        if (dataDeNascimento == null) {
            throw new IllegalArgumentException("Data de Nascimento invalida informada");
        }

        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filho filho = (Filho) o;
        return nome.equals(filho.nome) && dataDeNascimento.equals(filho.dataDeNascimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, dataDeNascimento);
    }

    public String getNome() {
        return nome;
    }
}
