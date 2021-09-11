package br.com.raphaelinacio.core.domain.pai.exception;

import br.com.raphaelinacio.core.domain.pai.Email;
import br.com.raphaelinacio.core.domain.pai.Pai;
import br.com.raphaelinacio.core.domain.pai.repository.PaiNaoCadastradoException;

public interface PaiRepository {
    void cadastrarPai(Pai pai);

    Pai buscarPaiPorEmail(Email email) throws PaiNaoCadastradoException;

    boolean verificarCadastroDeEmail(Email email) throws PaiNaoCadastradoException;

    void removerCadastro(Email email);
}
