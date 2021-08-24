package br.com.raphaelinacio.core.usecase.pai;

import br.com.raphaelinacio.core.DataBuilder;
import br.com.raphaelinacio.core.domain.pai.PaiNaoCadastradoException;
import br.com.raphaelinacio.core.domain.rotina.RotinaDTO;
import br.com.raphaelinacio.core.domain.rotina.TipoRecorrenciaEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

class CriarNovaRotinaTest extends DataBuilder {

    CriarNovaRotina criarNovaRotina = new CriarNovaRotina(paiRepository, atividadeRepository, rotinaRepository);

    @BeforeEach
    public void setup() {
        iniciarEscopoGlobal();
    }

    @Test
    void deveCriarUmaNovaRotina() throws PaiNaoCadastradoException {
        RotinaDTO rotinaDTO = new RotinaDTO(criarAtividadeDTO(), TipoRecorrenciaEnum.MENSAL.name(), LocalTime.now());
        criarNovaRotina.executar(email.getEndereco(), rotinaDTO);
    }
}