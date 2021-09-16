package br.com.raphaelinacio.infra.repository;

import br.com.raphaelinacio.core.domain.pai.Email;
import br.com.raphaelinacio.core.domain.pai.Pai;
import br.com.raphaelinacio.core.domain.pai.repository.PaiNaoCadastradoException;
import br.com.raphaelinacio.core.domain.pai.exception.PaiRepository;
import br.com.raphaelinacio.core.domain.rotina.Rotina;
import br.com.raphaelinacio.core.domain.rotina.exception.RotinaNaoCadastradaException;
import br.com.raphaelinacio.core.domain.rotina.repository.RotinaRepository;
import br.com.raphaelinacio.core.domain.rotina.TipoRotina;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class DatabaseMock {
    public Map<UUID, Rotina> repositorioDeRotinas = new HashMap<>();
    public Map<String, Pai> repositorioDePais = new HashMap<>();
    public RotinaRepository rotinaRepository = new RotinaRepository() {


        @Override
        public void criarRotina(Pai pai, Rotina rotina) {
            repositorioDeRotinas.put(rotina.getCodigo(), rotina);
            Pai paiEncontrado = repositorioDePais.get(pai.getEmail().getEndereco());
            paiEncontrado.criarRotinaParaMeuFilho(rotina);
        }

        @Override
        public void criarRotinaDeSistema(Rotina rotina) {
            repositorioDeRotinas.put(rotina.getCodigo(), rotina);
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

        @Override
        public List<Rotina> buscarRotinasPorTipo(TipoRotina tipoRotina) {
            List<Rotina> rotinasEncontradas = new ArrayList<>();

            repositorioDeRotinas.forEach((uuid, rotina) -> {
                if (rotina.getTipoRotina().equals(tipoRotina)) {
                    rotinasEncontradas.add(rotina);
                }
            });

            return rotinasEncontradas;
        }

        @Override
        public void remover(UUID codigoRotina) {
            repositorioDeRotinas.remove(codigoRotina);
        }

    };
    public PaiRepository paiRepository = new PaiRepository() {
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

        @Override
        public void removerCadastro(Email email) {
            repositorioDePais.remove(email.getEndereco());
        }
    };
}
