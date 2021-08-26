package br.com.raphaelinacio.core.usecase.pai;

import br.com.raphaelinacio.core.domain.pai.*;
import br.com.raphaelinacio.core.domain.rotina.*;

import java.util.UUID;

public class IncluirRotinaParaMeuFilho {

    private final PaiRepository paiRepository;
    private final RotinaRepository rotinaRepository;

    public IncluirRotinaParaMeuFilho(PaiRepository paiRepository, RotinaRepository atividadeRepository) {
        this.paiRepository = paiRepository;
        this.rotinaRepository = atividadeRepository;
    }

    void executar(String enderecoEmailPai, UUID codigoRotina) throws RotinaNaoCadastradaException, PaiNaoCadastradoException {
        Email email = new Email(enderecoEmailPai);

        Pai pai = paiRepository.buscarPaiPorEmail(email);

        Rotina rotina = rotinaRepository.buscarRotina(codigoRotina);

        rotinaRepository.associarRotina(pai, rotina);
    }

}