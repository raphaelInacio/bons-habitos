package br.com.raphaelinacio.core.domain.pai;

public interface PaiRepository {
    void cadastrarPai(Pai pai);

    Pai buscarPaiPorEmail(Email email) throws PaiNaoCadastradoException;

    boolean verificarCadastroDeEmail(Email email) throws PaiNaoCadastradoException;
}
