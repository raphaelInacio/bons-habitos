package br.com.raphaelinacio.domain.rotina;


import java.util.Objects;

public class Habito {

    private String nome;
    private String titulo;
    private String descricao;

    public Habito(String nome, String titulo, String descricao) {
        if (Objects.isNull(nome) || nome.length() == 0) {
            throw new IllegalArgumentException("Nome não informado");
        }

        if (Objects.isNull(titulo) || titulo.length() == 0) {
            throw new IllegalArgumentException("Titulo não informado");
        }


        if (Objects.isNull(descricao) || descricao.length() == 0) {
            throw new IllegalArgumentException("Descrição não informada");
        }

        this.nome = nome;
        this.titulo = titulo;
        this.descricao = descricao;
    }
}