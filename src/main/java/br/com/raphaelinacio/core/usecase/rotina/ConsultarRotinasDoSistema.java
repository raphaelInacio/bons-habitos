package br.com.raphaelinacio.core.usecase.rotina;

import br.com.raphaelinacio.core.domain.rotina.Rotina;
import br.com.raphaelinacio.core.domain.rotina.RotinaDTO;
import br.com.raphaelinacio.core.domain.rotina.RotinaRepository;
import br.com.raphaelinacio.core.domain.rotina.TipoRotina;

import java.util.List;
import java.util.UUID;

public class ConsultarRotinasDoSistema {

    private RotinaRepository rotinaRepository;

    public ConsultarRotinasDoSistema(RotinaRepository rotinaRepository) {
        this.rotinaRepository = rotinaRepository;
    }

    public List<RotinaDTO> executar() {
        List<Rotina> minhasRotinas = rotinaRepository.buscarRotinasPorTipo(TipoRotina.SISTEMA);
        return RotinaDTO.converter(minhasRotinas);
    }

    public RotinaDTO executar(String codigoRotina) {
        Rotina rotina = rotinaRepository.buscarRotina(UUID.fromString(codigoRotina));
        return RotinaDTO.converter(rotina);
    }
}
