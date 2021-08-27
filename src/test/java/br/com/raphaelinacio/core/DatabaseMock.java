package br.com.raphaelinacio.core;

import br.com.raphaelinacio.core.domain.pai.Email;
import br.com.raphaelinacio.core.domain.pai.Pai;
import br.com.raphaelinacio.core.domain.pai.PaiNaoCadastradoException;
import br.com.raphaelinacio.core.domain.pai.PaiRepository;
import br.com.raphaelinacio.core.domain.rotina.*;

import java.util.*;
import java.util.stream.Collectors;

public class DatabaseMock {

    public static Map<UUID, Atividade> repositorioDeAtividades = new HashMap<>();
    public static Map<UUID, Rotina> repositorioDeRotinas = new HashMap<>();
    public static Map<String, Pai> repositorioDePais = new HashMap<>();

    public static RotinaRepository rotinaRepository = new RotinaRepository() {


        @Override
        public void criarRotina(Pai pai, Rotina rotina) {
            repositorioDeRotinas.put(rotina.getCodigo(), rotina);
            Pai paiEncontrado = repositorioDePais.get(pai.getEmail().getEndereco());
            paiEncontrado.criarRotinaParaMeuFilho(rotina);
        }

        @Override
        public List<Rotina> buscarMinhasRotinas(Pai pai) {
            Pai paiEncontrado = repositorioDePais.get(pai.getEmail().getEndereco());
            return paiEncontrado.minhaRotina()
                    .stream()
                    .map(rotina -> repositorioDeRotinas.get(rotina.getCodigo()))
                    .collect(Collectors.toList());
        }

        @Override
        public Rotina buscarMinhaRotina(Pai pai, UUID codigoRotina) throws RotinaNaoCadastradaException {
            Pai paiEncontrado = repositorioDePais.get(pai.getEmail().getEndereco());
            return paiEncontrado.minhaRotina()
                    .stream()
                    .filter(minhaRotina -> minhaRotina.getCodigo().equals(codigoRotina))
                    .findFirst()
                    .orElseThrow(() -> new RotinaNaoCadastradaException("Não existe essa rotina"));
        }

        @Override
        public Rotina buscarRotina(UUID codigoRotina) throws RotinaNaoCadastradaException {
            if (repositorioDeRotinas.containsKey(codigoRotina))
                return repositorioDeRotinas.get(codigoRotina);
            throw new RotinaNaoCadastradaException("Rotina não cadastrada");
        }

        @Override
        public void associarRotina(Pai pai, Rotina rotina) throws RotinaNaoCadastradaException {
            if (!repositorioDeRotinas.containsKey(rotina.getCodigo()))
                throw new RotinaNaoCadastradaException("Rotina não cadastrada");
            Rotina rotinaEncontrada = this.buscarRotina(rotina.getCodigo());
        }

        @Override
        public void registrarParticipacao(Rotina rotina) {
            repositorioDeRotinas.put(rotina.getCodigo(), rotina);
        }

    };

    public static AtividadeRepository atividadeRepository = new AtividadeRepository() {


        @Override
        public Atividade buscarAtividade(UUID codigoAtividade) throws AtividadeNaoCadastradaException {
            if (repositorioDeAtividades.containsKey(codigoAtividade)) {
                return repositorioDeAtividades.get(codigoAtividade);
            }
            throw new AtividadeNaoCadastradaException("Não existe atividade cadastrada com esse ID");
        }

        @Override
        public void criarAtividade(Pai pai, Atividade atividade) {
            repositorioDeAtividades.put(atividade.getCodigo(), atividade);
        }

        @Override
        public void associarAtividade(Pai pai, Atividade atividade) {
            repositorioDeAtividades.put(atividade.getCodigo(), atividade);
        }
    };

    public static PaiRepository paiRepository = new PaiRepository() {


        @Override
        public void cadastrarPai(Pai pai) {
            System.out.println("Pai criado com sucesso");
            repositorioDePais.put(pai.getEmail().getEndereco(), pai);
        }

        @Override
        public Pai buscarPaiPorEmail(Email enderecoEmailPai) throws PaiNaoCadastradoException {
            if (repositorioDePais.containsKey(enderecoEmailPai.getEndereco())) {
                return repositorioDePais.get(enderecoEmailPai.getEndereco());
            } else throw new PaiNaoCadastradoException("Não existe um pai cadastrado para esse e-mail");
        }

        @Override
        public boolean verificarCadastroDeEmail(Email email) throws PaiNaoCadastradoException {
            return repositorioDePais.containsKey(email.getEndereco());
        }
    };
}
