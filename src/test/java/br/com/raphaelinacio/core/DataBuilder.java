package br.com.raphaelinacio.core;

import br.com.raphaelinacio.core.domain.pai.Email;
import br.com.raphaelinacio.core.domain.pai.Filho;
import br.com.raphaelinacio.core.domain.pai.Pai;
import br.com.raphaelinacio.core.domain.rotina.*;
import br.com.raphaelinacio.core.domain.rotina.dto.AtividadeDTO;
import br.com.raphaelinacio.core.domain.rotina.dto.RotinaDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public class DataBuilder extends DatabaseMock {

    public static Email email = new Email("contato.raphaelinacio@gmail.com");
    public static String nomeFilho = "Raphael Silva Dias";
    public static Atividade atividadeGlobal;
    public static Pai paiGlobal;
    public static Rotina rotinaGlobal;

    public Pai criarPai() {
        Pai pai = new Pai("Raphael Inacio", new Email("contato.raphaelinacio@gmail.com"));
        return pai;
    }

    public Filho criarFilho() {
        return new Filho(nomeFilho, LocalDate.of(2014, 7, 3));
    }

    public Pai criarPaiComFilho() {
        Pai pai = criarPai();
        pai.incluirFilho(criarFilho());
        return pai;
    }

    public Pai criarPaiComAtividade() {
        Pai pai = criarPaiComFilho();
        Atividade atividade = criarAtividadeSistema();
        pai.criarAtividadeParaMeuFilho(criarFilho(), atividade);
        return pai;
    }

    public Pai criarPaiComRotina() {
        Pai pai = criarPaiComAtividade();
        List<Atividade> atividades = pai.atividadesDoMeu(pai.meusFilhos().get(0));
        var rotina = new Rotina(criarRecorrencia(), atividades.get(0));
        pai.criarRotinaParaMeuFilho(rotina);
        return pai;
    }

    public void iniciarEscopoGlobal() {
        atividadeGlobal = criarAtividadeSistema();
        rotinaGlobal = criarRotinaSistema();
        paiGlobal = criarPaiComFilho();
        rotinaGlobal = criarRotinaSistema();
        rotinaRepository.criarRotinaDeSistema(criarRotinaSistema());
        paiRepository.cadastrarPai(paiGlobal);
        atividadeRepository.criarAtividade(paiGlobal, atividadeGlobal);
        rotinaRepository.criarRotina(paiGlobal, rotinaGlobal);
    }

    public Atividade criarAtividadeSistema() {
        return new Atividade(
                "Novo Habito",
                "Cadastrando um novo habito",
                "Este habito serve para testar a aplicação");
    }

    public AtividadeDTO criarAtividadeDTO() {
        return new AtividadeDTO(
                "Novo Habito",
                "Cadastrando um novo habito",
                "Este habito serve para testar a aplicação");
    }

    public Recorrencia criarRecorrencia() {
        return new Recorrencia(TipoRecorrenciaEnum.DIARIA, new HorarioRecorrencia(LocalTime.now()), LocalDate.now());
    }

    public Rotina criarRotinaSistema() {
        return new Rotina(criarRecorrencia(), criarAtividadeSistema());
    }

    public Rotina criarRotinaPai() {
        return new Rotina(criarRecorrencia(), criarAtividadeSistema(), TipoRotina.PAI);
    }

    public RotinaDTO criarRotinaDTO() {
        return new RotinaDTO(criarAtividadeDTO(), TipoRecorrenciaEnum.MENSAL.name(), LocalTime.now(), UUID.randomUUID());
    }
}
