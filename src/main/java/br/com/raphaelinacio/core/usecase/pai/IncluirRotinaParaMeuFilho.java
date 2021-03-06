package br.com.raphaelinacio.core.usecase.pai;

import br.com.raphaelinacio.core.domain.pai.Email;
import br.com.raphaelinacio.core.domain.pai.Pai;
import br.com.raphaelinacio.core.domain.pai.repository.PaiNaoCadastradoException;
import br.com.raphaelinacio.core.domain.pai.exception.PaiRepository;
import br.com.raphaelinacio.core.domain.rotina.Rotina;
import br.com.raphaelinacio.core.domain.rotina.TipoRotina;
import br.com.raphaelinacio.core.domain.rotina.exception.RotinaNaoCadastradaException;
import br.com.raphaelinacio.core.domain.rotina.repository.RotinaRepository;

import java.util.UUID;

public class IncluirRotinaParaMeuFilho {

    private final PaiRepository paiRepository;
    private final RotinaRepository rotinaRepository;

    public IncluirRotinaParaMeuFilho(PaiRepository paiRepository, RotinaRepository atividadeRepository) {
        this.paiRepository = paiRepository;
        this.rotinaRepository = atividadeRepository;
    }

    public void executar(String enderecoEmailPai, UUID codigoRotina) throws RotinaNaoCadastradaException, PaiNaoCadastradoException {
        Email email = new Email(enderecoEmailPai);
        Pai pai = paiRepository.buscarPaiPorEmail(email);
        Rotina rotina = rotinaRepository.buscarRotina(codigoRotina);
        rotina.registrarParticipacaoDiaria();
        rotinaRepository.associarRotina(pai, new Rotina(rotina.getRecorrencia(), rotina.getAtividade(), TipoRotina.PAI));
    }

}