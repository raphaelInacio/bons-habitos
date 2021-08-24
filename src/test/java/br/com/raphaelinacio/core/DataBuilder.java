package br.com.raphaelinacio.core;

import br.com.raphaelinacio.core.domain.pai.*;
import br.com.raphaelinacio.core.domain.rotina.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class DataBuilder {

    public static Email email = new Email("contato.raphaelinacio@gmail.com");
    public static String nomeFilho = "Raphael Silva Dias";
    public static Atividade atividadeGlobal;
    public static Pai paiGlobal;
    public static Rotina rotinaGlobal;


    public RotinaRepository rotinaRepository = new RotinaRepository() {

        Map<String, List<Rotina>> bancoDeRotinas = new HashMap<>();

        @Override
        public void criarRotina(Pai pai, Rotina rotina) {
            if (bancoDeRotinas.containsKey(pai.getEmail().getEndereco())) {
                List<Rotina> rotinas = bancoDeRotinas.get(pai.getEmail().getEndereco());
                rotinas.add(rotina);
            } else {
                bancoDeRotinas.put(pai.getEmail().getEndereco(), Arrays.asList(rotina));
            }
        }

        @Override
        public List<Rotina> buscarMinhasRotinas(Pai pai) {
            if (bancoDeRotinas.containsKey(pai.getEmail().getEndereco())) {
                return bancoDeRotinas.get(pai.getEmail().getEndereco());
            } else {
                return Collections.EMPTY_LIST;
            }
        }

    };

    public AtividadeRepository atividadeRepository = new AtividadeRepository() {

        Map<UUID, Atividade> bancoDeAtividades = new HashMap<>();

        @Override
        public Atividade buscarAtividade(UUID codigoAtividade) throws AtividadeNaoCadastradaException {
            if (bancoDeAtividades.containsKey(codigoAtividade)) {
                return bancoDeAtividades.get(codigoAtividade);
            }
            throw new AtividadeNaoCadastradaException("Não existe atividade cadastrada com esse ID");
        }

        @Override
        public void criarAtividade(Pai pai, Atividade atividade) {
            bancoDeAtividades.put(atividade.getCodigo(), atividade);
        }

        @Override
        public void associarAtividade(Pai pai, Atividade atividade) {
            bancoDeAtividades.put(atividade.getCodigo(), atividade);
        }
    };

    public PaiRepository paiRepository = new PaiRepository() {

        Map<String, Pai> bancoDeDados = new HashMap<String, Pai>();

        @Override
        public void cadastrarPai(Pai pai) {
            System.out.println("Pai criado com sucesso");
            bancoDeDados.put(pai.getEmail().getEndereco(), pai);
        }

        @Override
        public Pai buscarPaiPorEmail(Email enderecoEmailPai) throws PaiNaoCadastradoException {
            if (bancoDeDados.containsKey(enderecoEmailPai.getEndereco())) {
                return bancoDeDados.get(enderecoEmailPai.getEndereco());
            } else throw new PaiNaoCadastradoException("Não existe um pai cadastrado para esse e-mail");
        }

        @Override
        public boolean verificarCadastroDeEmail(Email email) throws PaiNaoCadastradoException {
            return bancoDeDados.containsKey(email.getEndereco());
        }
    };

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
        paiGlobal = criarPaiComFilho();
        rotinaGlobal = criarRotina();
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

    public Rotina criarRotina() {
        return new Rotina(criarRecorrencia(), criarAtividadeSistema());
    }

}
