package br.com.raphaelinacio.infra;

import br.com.raphaelinacio.core.domain.pai.PaiRepository;
import br.com.raphaelinacio.core.domain.rotina.RotinaRepository;
import br.com.raphaelinacio.core.usecase.pai.BuscarMinhasInformacoes;
import br.com.raphaelinacio.core.usecase.pai.CadastrarPaiNoSistema;
import br.com.raphaelinacio.core.usecase.pai.CriarNovaRotina;
import br.com.raphaelinacio.core.usecase.pai.RegistrarParticipacao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config extends DatabaseMock {

    @Bean
    public PaiRepository paiRepository() {
        return paiRepository;
    }

    @Bean
    public RotinaRepository rotinaRepository() {
        return rotinaRepository;
    }

    @Bean
    public CadastrarPaiNoSistema cadastrarPaiNoSistema(PaiRepository paiRepository) {
        return new CadastrarPaiNoSistema(paiRepository);
    }

    @Bean
    public BuscarMinhasInformacoes buscarMinhasInformacoes(PaiRepository paiRepository) {
        return new BuscarMinhasInformacoes(paiRepository);
    }

    @Bean
    public CriarNovaRotina criarNovaRotina(PaiRepository paiRepository, RotinaRepository rotinaRepository) {
        return new CriarNovaRotina(paiRepository, rotinaRepository);
    }

    @Bean
    public RegistrarParticipacao registrarParticipacao(PaiRepository paiRepository, RotinaRepository rotinaRepository) {
        return new RegistrarParticipacao(paiRepository, rotinaRepository);
    }
}
