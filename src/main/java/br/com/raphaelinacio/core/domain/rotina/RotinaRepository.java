package br.com.raphaelinacio.core.domain.rotina;

import br.com.raphaelinacio.core.domain.pai.Pai;

import java.util.List;
import java.util.UUID;

public interface RotinaRepository {
    void criarRotina(Pai pai, Rotina rotina);

    void criarRotinaDeSistema(Rotina rotina);

    List<Rotina> buscarMinhasRotinas(Pai pai);

    Rotina buscarMinhaRotina(Pai pai, UUID codigoRotina) throws RotinaNaoCadastradaException;

    Rotina buscarRotina(UUID codigoRotina) throws RotinaNaoCadastradaException;

    void associarRotina(Pai pai, Rotina rotina) throws RotinaNaoCadastradaException;

    void registrarParticipacao(Rotina rotina);

    List<Rotina> buscarRotinasPorTipo(TipoRotina tipoRotina);
}
