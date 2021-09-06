package br.com.raphaelinacio.core.usecase.rotina;

import br.com.raphaelinacio.core.domain.rotina.RotinaDTO;
import br.com.raphaelinacio.core.domain.rotina.RotinaRepository;

public class CriarRotinaSistema {
    private RotinaRepository rotinaRepository;

    public CriarRotinaSistema(RotinaRepository rotinaRepository) {
        this.rotinaRepository = rotinaRepository;
    }

    public void executar(RotinaDTO rotinaDTO) {
        rotinaRepository.criarRotinaDeSistema(rotinaDTO.converterParaRotinaSistema());
    }
}
