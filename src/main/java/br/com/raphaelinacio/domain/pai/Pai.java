package br.com.raphaelinacio.domain.pai;

import br.com.raphaelinacio.domain.rotina.Atividade;
import br.com.raphaelinacio.domain.rotina.Rotina;

import java.util.*;

public class Pai {
    private String nome;
    private Email email;
    private List<Filho> filhos = new ArrayList<>();
    private Map<Filho, List<Atividade>> atividadesParaMeusFilhos = new HashMap<>();
    private List<Rotina> rotinasParaMeusFilhos = new ArrayList<>();

    public Pai(String nome, Email email) {
        if (nome == null || nome == "") throw new IllegalArgumentException("Nome invalido cadastrado.");
        this.nome = nome;
        this.email = email;
    }

    public void incluirFilho(Filho filho) {
        this.filhos.add(filho);
    }

    public List<Filho> meusFilhos() {
        return this.filhos;
    }

    public List<Atividade> atividadesDoMeu(Filho filho) {
        return this.atividadesParaMeusFilhos.get(filho);
    }

    public void criarRotinaParaMeuFilho(Rotina rotina) {
        this.rotinasParaMeusFilhos.add(rotina);
    }

    public List<Rotina> minhaRotina() {
        return this.rotinasParaMeusFilhos;
    }

    public void registrarParticipacaoDiaria(Rotina rotina) {
        rotina.registrarParticipacaoDiaria();
    }

    public void criarAtividadeParaMeuFilho(Filho filho, Atividade atividade) {
        if (!this.atividadesParaMeusFilhos.containsKey(filho)) {
            this.atividadesParaMeusFilhos.put(filho, Arrays.asList(atividade));
        } else {
            var habitosDoMeuFilho = this.atividadesParaMeusFilhos.get(filho);
            habitosDoMeuFilho.add(atividade);
        }
    }

}
