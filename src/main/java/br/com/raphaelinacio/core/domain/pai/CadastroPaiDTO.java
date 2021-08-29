package br.com.raphaelinacio.core.domain.pai;

import java.time.LocalDate;

public class CadastroPaiDTO {
    private final String nomeDoPai;
    private final String enderecoEmailPai;
    private final String nomeFilho;
    private final LocalDate dataNascimentoFilho;

    public CadastroPaiDTO(String nomeDoPai, String enderecoEmailPai, String nomeFilho, LocalDate dataNascimentoFilho) {
        this.nomeDoPai = nomeDoPai;
        this.enderecoEmailPai = enderecoEmailPai;
        this.nomeFilho = nomeFilho;
        this.dataNascimentoFilho = dataNascimentoFilho;
    }

    public String getNomeDoPai() {
        return nomeDoPai;
    }

    public String getEnderecoEmailPai() {
        return enderecoEmailPai;
    }

    public String getNomeFilho() {
        return nomeFilho;
    }

    public LocalDate getDataNascimentoFilho() {
        return dataNascimentoFilho;
    }
}
