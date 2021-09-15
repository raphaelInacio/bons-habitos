package br.com.raphaelinacio.infra.config;

import br.com.raphaelinacio.core.domain.pai.exception.PaiRepository;
import br.com.raphaelinacio.core.domain.rotina.repository.RotinaRepository;
import br.com.raphaelinacio.core.usecase.pai.*;
import br.com.raphaelinacio.core.usecase.rotina.ConsultarRotinasDoSistema;
import br.com.raphaelinacio.core.usecase.rotina.CriarRotinaSistema;
import br.com.raphaelinacio.infra.repository.DatabaseMock;
import br.com.raphaelinacio.infra.repository.datastore.PaiRepositoryDataStoreImpl;
import br.com.raphaelinacio.infra.repository.datastore.RotinaRepositoryDataStoreImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config extends DatabaseMock {

    @Bean
    public PaiRepository paiRepository() {
        return paiRepository;
    }

    @Bean("paiRepositoryDataStoreImpl")
    public PaiRepository paiRepositoryDataStoreImpl() {
        return new PaiRepositoryDataStoreImpl();
    }

    @Bean("rotinaRepositoryDataStoreImpl")
    public RotinaRepository rotinaRepositoryDataStoreImpl() {
        return new RotinaRepositoryDataStoreImpl();
    }

    @Bean
    public RotinaRepository rotinaRepository() {
        return rotinaRepository;
    }

    @Bean
    public CadastrarPaiNoSistema cadastrarPaiNoSistema(@Qualifier("paiRepositoryDataStoreImpl") PaiRepository paiRepository) {
        return new CadastrarPaiNoSistema(paiRepository);
    }

    @Bean
    public BuscarMinhasInformacoes buscarMinhasInformacoes(@Qualifier("paiRepositoryDataStoreImpl") PaiRepository paiRepository) {
        return new BuscarMinhasInformacoes(paiRepository);
    }

    @Bean
    public ConsultarMinhasRotinas consultarMinhasRotinas(PaiRepository paiRepository, RotinaRepository rotinaRepository) {
        return new ConsultarMinhasRotinas(paiRepository, rotinaRepository);
    }

    @Bean
    public CriarNovaRotina criarNovaRotina(PaiRepository paiRepository, RotinaRepository rotinaRepository) {
        return new CriarNovaRotina(paiRepository, rotinaRepository);
    }

    @Bean
    public RegistrarParticipacao registrarParticipacao(PaiRepository paiRepository, RotinaRepository rotinaRepository) {
        return new RegistrarParticipacao(paiRepository, rotinaRepository);
    }

    @Bean
    public CriarRotinaSistema criarRotinaSistema(@Qualifier("rotinaRepositoryDataStoreImpl") RotinaRepository rotinaRepository) {
        return new CriarRotinaSistema(rotinaRepository);
    }

    @Bean
    public ConsultarRotinasDoSistema consultarRotinasDoSistema(@Qualifier("rotinaRepositoryDataStoreImpl") RotinaRepository rotinaRepository) {
        return new ConsultarRotinasDoSistema(rotinaRepository);
    }

    @Bean("mock-api")
    public DatabaseMock databaseMock() {
        return new DatabaseMock();
    }
}
