package br.com.raphaelinacio.core.usecase.pai;

import br.com.raphaelinacio.core.domain.pai.Email;
import br.com.raphaelinacio.core.domain.pai.Pai;
import br.com.raphaelinacio.core.domain.pai.PaiNaoCadastradoException;
import br.com.raphaelinacio.core.domain.pai.PaiRepository;
import br.com.raphaelinacio.core.domain.rotina.AtividadeRepository;
import br.com.raphaelinacio.core.domain.rotina.Rotina;
import br.com.raphaelinacio.core.domain.rotina.RotinaDTO;
import br.com.raphaelinacio.core.domain.rotina.RotinaRepository;

public class CriarNovaRotina {

    private PaiRepository paiRepository;
    private AtividadeRepository atividadeRepository;
    private RotinaRepository rotinaRepository;

    public CriarNovaRotina(PaiRepository paiRepository, AtividadeRepository atividadeRepository, RotinaRepository rotinaRepository) {
        this.paiRepository = paiRepository;
        this.atividadeRepository = atividadeRepository;
        this.rotinaRepository = rotinaRepository;
    }

    void executar(String enderecoDeEmail, RotinaDTO rotinaDTO) throws PaiNaoCadastradoException {
        var email = new Email(enderecoDeEmail);

        Pai pai = paiRepository.buscarPaiPorEmail(email);

        Rotina rotina = rotinaDTO.converterParaDominio();

        rotinaRepository.criarRotina(pai, rotina);
    }

}