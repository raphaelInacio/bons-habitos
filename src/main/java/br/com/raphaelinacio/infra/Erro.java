package br.com.raphaelinacio.infra;

public class Erro {
    public String getMensagem() {
        return mensagem;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public Erro(String mensagem, Integer codigo) {
        this.mensagem = mensagem;
        this.codigo = codigo;
    }

    private String mensagem;
    private Integer codigo;
}
