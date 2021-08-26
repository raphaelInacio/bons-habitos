package br.com.raphaelinacio.core.domain.rotina;

import br.com.raphaelinacio.core.domain.pai.Pai;

import java.util.List;
import java.util.UUID;

public interface RotinaRepository {
    void criarRotina(Pai pai, Rotina rotina);

    List<Rotina> buscarMinhasRotinas(Pai pai);

    Rotina buscarRotina(UUID codigoRotina) throws RotinaNaoCadastradaException;

    void associarRotina(Pai pai, Rotina rotina) throws RotinaNaoCadastradaException;
}
