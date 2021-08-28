package br.com.raphaelinacio.core.domain.pai;

import br.com.raphaelinacio.core.domain.rotina.RotinaDTO;

import java.util.List;

public class PaiDTO {
    private String email;
    private String nome;
    private FilheDTO filho;
    private List<RotinaDTO> rotinas;

    public PaiDTO(Pai pai) {
        this.nome = pai.getNome();
        this.email = pai.getEmail().getEndereco();
        this.filho = new FilheDTO(pai.meusFilhos().stream().findFirst().orElse(null));
        this.rotinas = RotinaDTO.converte(pai.minhaRotina());
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public FilheDTO getFilho() {
        return filho;
    }

    public List<RotinaDTO> getRotinas() {
        return rotinas;
    }
}
