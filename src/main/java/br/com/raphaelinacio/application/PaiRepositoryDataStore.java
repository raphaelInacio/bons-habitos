package br.com.raphaelinacio.application;

import br.com.raphaelinacio.core.domain.pai.Pai;
import br.com.raphaelinacio.core.domain.pai.PaiRepository;

public class PaiRepositoryDataStore implements PaiRepository {
    @Override
    public void cadastrarPai(Pai pai) {

    }

    @Override
    public Pai buscarPaiPorEmail(String enderecoEmailPai) {
        return null;
    }

    @Override
    public void incluirFilho(Pai pai) {

    }
}
