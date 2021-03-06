package br.com.raphaelinacio.infra.repository.datastore;

import br.com.raphaelinacio.core.domain.pai.Email;
import br.com.raphaelinacio.core.domain.pai.Pai;
import br.com.raphaelinacio.core.domain.pai.exception.PaiRepository;
import br.com.raphaelinacio.core.domain.pai.repository.PaiNaoCadastradoException;
import br.com.raphaelinacio.infra.mapper.PaiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class PaiRepositoryDataStoreImpl implements PaiRepository {

    @Autowired
    private PaiDataStoreRepository repository;

    @Autowired
    private PaiMapper paiMapper;

    @Override
    public void cadastrarPai(Pai pai) {
        var entidadePai = paiMapper.paraEntidade(pai);
        repository.save(entidadePai);
    }

    @Override
    public Pai buscarPaiPorEmail(Email email) throws PaiNaoCadastradoException {
        var pai = repository.findByEmail(email.getEndereco());

        if (Objects.isNull(pai)) {
            throw new PaiNaoCadastradoException("Não existe um pai cadastrado com esse e-mail");
        }

        return paiMapper.paraDominio(pai);
    }

    @Override
    public boolean verificarCadastroDeEmail(Email email) throws PaiNaoCadastradoException {
        var pai = repository.findByEmail(email.getEndereco());
        return Objects.nonNull(pai);
    }

    @Override
    public void removerCadastro(Email email) {
        repository.deleteByEmail(email.getEndereco());
    }
}
