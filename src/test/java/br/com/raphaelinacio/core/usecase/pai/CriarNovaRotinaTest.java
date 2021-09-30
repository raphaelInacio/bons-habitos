package br.com.raphaelinacio.core.usecase.pai;

import br.com.raphaelinacio.core.DataBuilder;
import br.com.raphaelinacio.core.domain.pai.repository.PaiNaoCadastradoException;
import br.com.raphaelinacio.core.domain.rotina.dto.RotinaDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class CriarNovaRotinaTest extends DataBuilder {

    CriarNovaRotina criarNovaRotina = new CriarNovaRotina(paiRepository, rotinaRepository);

    @BeforeEach
    public void setup() {
        iniciarEscopoGlobal();
    }

    @Test
    void deveCriarUmaNovaRotina() throws PaiNaoCadastradoException {
        RotinaDTO rotinaDTO = criarRotinaDTO();
        criarNovaRotina.executar(email.getEndereco(), rotinaDTO);
    }

}