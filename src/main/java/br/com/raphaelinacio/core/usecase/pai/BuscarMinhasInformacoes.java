package br.com.raphaelinacio.core.usecase.pai;

import br.com.raphaelinacio.core.domain.pai.*;

public class BuscarMinhasInformacoes {

    private PaiRepository paiRepository;

    public BuscarMinhasInformacoes(PaiRepository paiRepository) {
        this.paiRepository = paiRepository;
    }

    public PaiDTO executar(String enderecoEmailPai) throws PaiNaoCadastradoException {
        Email email = new Email(enderecoEmailPai);
        Pai pai = paiRepository.buscarPaiPorEmail(email);
        return new PaiDTO(pai);
    }
}
