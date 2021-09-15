package br.com.raphaelinacio.core.domain.pai.dto;

import br.com.raphaelinacio.core.domain.pai.Pai;
import br.com.raphaelinacio.core.domain.rotina.dto.RotinaDTO;

import java.util.List;

public class PaiDTO {
    private String email;
    private String nome;
    private List<FilheDTO> filhos;
    private List<RotinaDTO> rotinas;

    public PaiDTO(Pai pai) {
        this.nome = pai.getNome();
        this.email = pai.getEmail().getEndereco();
        this.filhos = FilheDTO.converter(pai.meusFilhos());
        this.rotinas = RotinaDTO.converter(pai.minhaRotina());
    }

    public PaiDTO() {
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public List<RotinaDTO> getRotinas() {
        return rotinas;
    }

    public List<FilheDTO> getFilhos() {
        return filhos;
    }
}
