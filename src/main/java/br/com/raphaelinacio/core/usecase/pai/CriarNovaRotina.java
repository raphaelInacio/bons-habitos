package br.com.raphaelinacio.core.usecase.pai;

import br.com.raphaelinacio.core.domain.pai.Email;
import br.com.raphaelinacio.core.domain.pai.Pai;
import br.com.raphaelinacio.core.domain.pai.repository.PaiNaoCadastradoException;
import br.com.raphaelinacio.core.domain.pai.exception.PaiRepository;
import br.com.raphaelinacio.core.domain.rotina.Rotina;
import br.com.raphaelinacio.core.domain.rotina.dto.RotinaDTO;
import br.com.raphaelinacio.core.domain.rotina.repository.RotinaRepository;

public class CriarNovaRotina {

    private PaiRepository paiRepository;
    private RotinaRepository rotinaRepository;

    public CriarNovaRotina(PaiRepository paiRepository, RotinaRepository rotinaRepository) {
        this.paiRepository = paiRepository;
        this.rotinaRepository = rotinaRepository;
    }

    public void executar(String enderecoDeEmail, RotinaDTO rotinaDTO) throws PaiNaoCadastradoException {
        var email = new Email(enderecoDeEmail);

        Pai pai = paiRepository.buscarPaiPorEmail(email);

        Rotina rotina = rotinaDTO.converterParaRotinaPai();

        rotinaRepository.criarRotina(pai, rotina);
    }

}
