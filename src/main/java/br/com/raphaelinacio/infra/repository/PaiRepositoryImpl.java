package br.com.raphaelinacio.infra.repository;

import br.com.raphaelinacio.core.domain.pai.Email;
import br.com.raphaelinacio.core.domain.pai.Pai;
import br.com.raphaelinacio.core.domain.pai.exception.PaiRepository;
import br.com.raphaelinacio.core.domain.pai.repository.PaiNaoCadastradoException;
import br.com.raphaelinacio.infra.mapper.PaiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.data.datastore.core.DatastoreTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PaiRepositoryImpl implements PaiRepository {

    @Autowired
    private DatastoreTemplate datastoreTemplate;

    @Autowired
    private PaiMapper paiMapper;

    @Override
    public void cadastrarPai(Pai pai) {
        datastoreTemplate.save(paiMapper.paraEntidade(pai));
    }

    @Override
    public Pai buscarPaiPorEmail(Email email) throws PaiNaoCadastradoException {
        return null;
    }

    @Override
    public boolean verificarCadastroDeEmail(Email email) throws PaiNaoCadastradoException {
        return false;
    }

    @Override
    public void removerCadastro(Email email) {

    }
}
