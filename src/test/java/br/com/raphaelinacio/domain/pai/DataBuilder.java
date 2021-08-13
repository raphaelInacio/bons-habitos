package br.com.raphaelinacio.domain.pai;

import br.com.raphaelinacio.domain.rotina.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class DataBuilder {

    public Pai criarPai() {
        Pai pai = new Pai("Raphael Inacio", new Email("contato.raphaelinacio@gmail.com"));
        return pai;
    }

    public Filho criarFilho() {
        return new Filho("Raphael Silva Dias", LocalDate.of(2014, 7, 3));
    }

    public Pai criarPaiComFilho() {
        Pai pai = criarPai();
        pai.incluirFilho(criarFilho());
        return pai;
    }

    public Pai criarPaiComAtividade() {
        Pai pai = criarPaiComFilho();
        Atividade atividade = criarAtividade();
        pai.criarAtividadeParaMeuFilho(criarFilho(), atividade);
        return pai;
    }

    public Pai criarPaiComRotina() {
        Pai pai = criarPaiComAtividade();
        List<Atividade> atividades = pai.atividadesDoMeu(pai.meusFilhos().get(0));
        var rotina = new Rotina(criarRecorrencia(), atividades.get(0), pai.meusFilhos().get(0));
        pai.criarRotinaParaMeuFilho(rotina);
        return pai;
    }

    public Atividade criarAtividade() {
        return new Atividade(
                "Novo Habito",
                "Cadastrando um novo habito",
                "Este habito serve para testar a aplicação");
    }

    public Recorrencia criarRecorrencia() {
        return new Recorrencia(TipoRecorrenciaEnum.DIARIA, new HorarioRecorrencia(LocalTime.now()), LocalDate.now());
    }

    public Rotina criarRotina() {
        return new Rotina(criarRecorrencia(), criarAtividade(), criarFilho());
    }

}
