package br.com.raphaelinacio.domain.pai;

import br.com.raphaelinacio.domain.rotina.Rotina;
import br.com.raphaelinacio.domain.rotina.Habito;

import java.util.*;

public class Pai {
    private String nome;
    private Email email;
    private List<Filho> filhos = new ArrayList<>();
    private Map<Filho, List<Habito>> habitosParaMeusFilhos = new HashMap<>();
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

    public Map<Filho, List<Habito>> habitosDosMeusFilhos() {
        return this.habitosParaMeusFilhos;
    }

    public List<Habito> habitosDoMeu(Filho filho) {
        return this.habitosParaMeusFilhos.get(filho);
    }

    public void criarRotinaParaMeuFilho(Rotina rotina) {

    }

    public void criarHabitoParaMeuFilho(Filho filho, Habito habito) {
        if (!this.habitosParaMeusFilhos.containsKey(filho)) {
            this.habitosParaMeusFilhos.put(filho, Arrays.asList(habito));
        } else {
            var habitosDoMeuFilho = this.habitosParaMeusFilhos.get(filho);
            habitosDoMeuFilho.add(habito);
        }
    }

}
