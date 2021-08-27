package br.com.raphaelinacio.core.usecase.pai;

import br.com.raphaelinacio.core.domain.pai.Email;
import br.com.raphaelinacio.core.domain.pai.Pai;
import br.com.raphaelinacio.core.domain.pai.PaiNaoCadastradoException;
import br.com.raphaelinacio.core.domain.pai.PaiRepository;
import br.com.raphaelinacio.core.domain.rotina.Rotina;
import br.com.raphaelinacio.core.domain.rotina.RotinaNaoCadastradaException;
import br.com.raphaelinacio.core.domain.rotina.RotinaRepository;

import java.util.UUID;

public class RegistrarParticipacao {
    private PaiRepository paiRepository;
    private RotinaRepository rotinaRepository;

    public RegistrarParticipacao(PaiRepository paiRepository, RotinaRepository rotinaRepository) {
        this.paiRepository = paiRepository;
        this.rotinaRepository = rotinaRepository;
    }

    void executar(String enderecoEmailPai, UUID codigoRotina) throws PaiNaoCadastradoException, RotinaNaoCadastradaException {
        Pai pai = paiRepository.buscarPaiPorEmail(new Email(enderecoEmailPai));
        Rotina rotina = rotinaRepository.buscarMinhaRotina(pai, codigoRotina);
        rotina.registrarParticipacaoDiaria();
        rotinaRepository.registrarParticipacao(rotina);
    }
}
