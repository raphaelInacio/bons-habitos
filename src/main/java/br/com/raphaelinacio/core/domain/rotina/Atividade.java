package br.com.raphaelinacio.core.domain.rotina;


import java.util.Objects;
import java.util.UUID;

public class Atividade {

    private UUID codigo;
    private String nome;
    private String titulo;
    private String descricao;
    private TipoRotina tipoRotina;

    public Atividade(String nome, String titulo, String descricao) {

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
        this.codigo = UUID.randomUUID();
        this.tipoRotina = TipoRotina.SISTEMA;
    }

    public UUID getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public TipoRotina getTipoAtividade() {
        return tipoRotina;
    }
}