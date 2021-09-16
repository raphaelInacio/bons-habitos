package br.com.raphaelinacio.core.usecase.pai;

import br.com.raphaelinacio.core.domain.pai.Email;
import br.com.raphaelinacio.core.domain.pai.Pai;
import br.com.raphaelinacio.core.domain.pai.repository.PaiNaoCadastradoException;
import br.com.raphaelinacio.core.domain.pai.exception.PaiRepository;
import br.com.raphaelinacio.core.domain.rotina.Rotina;
import br.com.raphaelinacio.core.domain.rotina.exception.RotinaNaoCadastradaException;
import br.com.raphaelinacio.core.domain.rotina.repository.RotinaRepository;

import java.util.UUID;

public class RegistrarParticipacao {
    private PaiRepository paiRepository;
    private RotinaRepository rotinaRepository;

    public RegistrarParticipacao(PaiRepository paiRepository, RotinaRepository rotinaRepository) {
        this.paiRepository = paiRepository;
        this.rotinaRepository = rotinaRepository;
    }

    public void executar(String enderecoEmailPai, UUID codigoRotina) throws PaiNaoCadastradoException, RotinaNaoCadastradaException {
        paiRepository.verificarCadastroDeEmail(new Email(enderecoEmailPai));
        Rotina rotina = rotinaRepository.buscarRotina(codigoRotina);
        rotina.registrarParticipacaoDiaria();
        rotinaRepository.registrarParticipacao(rotina);
    }
}
