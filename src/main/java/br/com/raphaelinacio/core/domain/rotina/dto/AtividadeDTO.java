package br.com.raphaelinacio.core.domain.rotina.dto;

import br.com.raphaelinacio.core.domain.rotina.Atividade;

public class AtividadeDTO {
    private String nome;
    private String titulo;
    private String descricao;

    public String getNome() {
        return nome;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public AtividadeDTO(String nome, String titulo, String descricao) {
        this.nome = nome;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Atividade converterParaDominio() {
        return new Atividade(
                this.nome,
                this.titulo,
                this.descricao
        );
    }
}
