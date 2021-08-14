package br.com.raphaelinacio.core.domain.pai;

public interface PaiRepository {
    void cadastrarPai(Pai pai);

    Pai buscarPaiPorEmail(String enderecoEmailPai);

    void incluirFilho(Pai pai);
}
