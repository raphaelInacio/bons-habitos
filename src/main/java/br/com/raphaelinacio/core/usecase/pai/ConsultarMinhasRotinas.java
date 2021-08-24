package br.com.raphaelinacio.core.usecase.pai;

import br.com.raphaelinacio.core.domain.pai.Email;
import br.com.raphaelinacio.core.domain.pai.Pai;
import br.com.raphaelinacio.core.domain.pai.PaiNaoCadastradoException;
import br.com.raphaelinacio.core.domain.pai.PaiRepository;
import br.com.raphaelinacio.core.domain.rotina.Rotina;
import br.com.raphaelinacio.core.domain.rotina.RotinaDTO;
import br.com.raphaelinacio.core.domain.rotina.RotinaRepository;

import java.util.Collections;
import java.util.List;

public class ConsultarMinhasRotinas {

    private PaiRepository paiRepository;
    private RotinaRepository rotinaRepository;

    public ConsultarMinhasRotinas(PaiRepository paiRepository, RotinaRepository rotinaRepository) {
        this.paiRepository = paiRepository;
        this.rotinaRepository = rotinaRepository;
    }

    public List<RotinaDTO> executar(String enderecoEmail) throws PaiNaoCadastradoException {
        Email email = new Email(enderecoEmail);

        Pai pai = paiRepository.buscarPaiPorEmail(email);

        List<Rotina> minhasRotinas = rotinaRepository.buscarMinhasRotinas(pai);

        if (minhasRotinas.isEmpty()) {
            return Collections.emptyList();
        }

        return RotinaDTO.converte(minhasRotinas);
    }
}
