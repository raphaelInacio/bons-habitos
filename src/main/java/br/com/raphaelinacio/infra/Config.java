package br.com.raphaelinacio.infra;

import br.com.raphaelinacio.core.domain.pai.PaiRepository;
import br.com.raphaelinacio.core.usecase.pai.BuscarMinhasInformacoes;
import br.com.raphaelinacio.core.usecase.pai.CadastrarPaiNoSistema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config extends DatabaseMock {

    @Bean
    public PaiRepository paiRepository() {
        return paiRepository;
    }

    @Bean
    public CadastrarPaiNoSistema cadastrarPaiNoSistema(PaiRepository paiRepository) {
        return new CadastrarPaiNoSistema(paiRepository);
    }

    @Bean
    public BuscarMinhasInformacoes buscarMinhasInformacoes(PaiRepository paiRepository) {
        return new BuscarMinhasInformacoes(paiRepository);
    }
}
