package br.com.raphaelinacio.domain.pai;

import br.com.raphaelinacio.domain.rotina.Atividade;
import br.com.raphaelinacio.domain.rotina.Rotina;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class PaiTest extends DataBuilder {

    @Test
    void novaDeveCriarUmPaiComDadosInvalidos() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Pai("", new Email("contato.raphaelinacio@gmail.com")));
    }

    @Test
    void incluirFilho() {
        Pai pai = criarPai();
        pai.incluirFilho(criarFilho());
        Assertions.assertEquals(1, pai.meusFilhos().size());
    }

    @Test
    void deveCriarAtividadeParaMeuFilho() {
        Pai pai = criarPaiComFilho();
        Atividade atividade = criarAtividade();
        pai.criarAtividadeParaMeuFilho(criarFilho(), atividade);
        List<Atividade> atividades = pai.atividadesDoMeu(pai.meusFilhos().get(0));
        Assertions.assertEquals(atividade, atividades.stream().findFirst().get());
    }

    @Test
    void deveCriarRotinasCorretamente() {
        var pai = criarPaiComRotina();
        Assertions.assertEquals(1, pai.minhaRotina().size());
    }

    @Test
    void deveCriarRegistrarParcipacaoDiariaNaRotinaCorretamente() {
        var pai = criarPaiComRotina();
        Assertions.assertEquals(1, pai.minhaRotina().size());
        Rotina rotina = pai.minhaRotina().stream().findFirst().get();
        pai.registrarParticipacaoDiaria(rotina);
        Assertions.assertEquals(1, rotina.verHistoricoDeParticipacoes().size());
    }

}