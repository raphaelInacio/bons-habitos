package br.com.raphaelinacio.core.domain.rotina;

import br.com.raphaelinacio.core.domain.pai.Pai;

import java.util.List;

public interface RotinaRepository {
    void criarRotina(Pai pai, Rotina rotina);
    List<Rotina> buscarMinhasRotinas(Pai pai);
}
