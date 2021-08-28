package br.com.raphaelinacio.core.usecase.pai;

import br.com.raphaelinacio.core.domain.rotina.Rotina;
import br.com.raphaelinacio.core.domain.rotina.RotinaDTO;
import br.com.raphaelinacio.core.domain.rotina.RotinaRepository;
import br.com.raphaelinacio.core.domain.rotina.TipoRotina;

import java.util.List;

public class ConsultarRotinasDoSistema {

    private RotinaRepository rotinaRepository;

    public ConsultarRotinasDoSistema(RotinaRepository rotinaRepository) {
        this.rotinaRepository = rotinaRepository;
    }

    public List<RotinaDTO> executar() {
        List<Rotina> minhasRotinas = rotinaRepository.buscarRotinasPorTipo(TipoRotina.SISTEMA);
        return RotinaDTO.converte(minhasRotinas);
    }
}
