package br.com.raphaelinacio.domain.pai;

import br.com.raphaelinacio.domain.rotina.Habito;
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
    void criarAtividadeParaMeuFilho() {
        Pai pai = criarPaiComFilho();
        Habito habito = criarHabito();
        pai.criarHabitoParaMeuFilho(criarFilho(), habito);
        List<Habito> habitos = pai.habitosDoMeu(pai.meusFilhos().get(0));
        Assertions.assertEquals(habito, habitos.stream().findFirst().get());
    }


}