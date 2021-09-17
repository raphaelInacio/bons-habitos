package br.com.raphaelinacio.infra.repository.datastore;

import br.com.raphaelinacio.core.domain.pai.Pai;
import br.com.raphaelinacio.core.domain.rotina.Rotina;
import br.com.raphaelinacio.core.domain.rotina.TipoRotina;
import br.com.raphaelinacio.core.domain.rotina.exception.RotinaNaoCadastradaException;
import br.com.raphaelinacio.core.domain.rotina.repository.RotinaRepository;
import br.com.raphaelinacio.infra.database.PaiEntity;
import br.com.raphaelinacio.infra.database.RotinaEntity;
import br.com.raphaelinacio.infra.mapper.PaiMapper;
import br.com.raphaelinacio.infra.mapper.RotinaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class RotinaRepositoryDataStoreImpl implements RotinaRepository {

    @Autowired
    private PaiDataStoreRepository paiDataStoreRepository;

    @Autowired
    private RotinaDataStoreRepository rotinaDataStoreRepository;

    @Autowired
    private PaiMapper paiMapper;

    @Autowired
    private RotinaMapper rotinaMapper;

    @Override
    public void criarRotina(Pai pai, Rotina rotina) {
        RotinaEntity rotinaSalva = rotinaDataStoreRepository.save(rotinaMapper.paraEntidade(rotina));
        PaiEntity paiEntity = paiDataStoreRepository.findByEmail(pai.getEmail().getEndereco());
        paiEntity.addicionaRotina(rotinaSalva.getId());
        paiDataStoreRepository.save(paiEntity);
    }

    @Override
    public void criarRotinaDeSistema(Rotina rotina) {
        rotinaDataStoreRepository.save(rotinaMapper.paraEntidade(rotina));
    }

    @Override
    public List<Rotina> buscarMinhasRotinas(Pai pai) {
        PaiEntity entidadePai = paiDataStoreRepository.findByEmail(pai.getEmail().getEndereco());
        Iterable<RotinaEntity> minhasRotinasSalvas = rotinaDataStoreRepository.findAllById(entidadePai.getRotinas());
        List<Rotina> minhasRotinas = new ArrayList<>();
        minhasRotinasSalvas.forEach(rotinaEntity -> minhasRotinas.add(rotinaMapper.paraDominio(rotinaEntity)));
        return minhasRotinas;
    }

    @Override
    public Rotina buscarMinhaRotina(Pai pai, UUID codigoRotina) throws RotinaNaoCadastradaException {
        return null;
    }

    @Override
    public Rotina buscarRotina(UUID codigoRotina) throws RotinaNaoCadastradaException {
        RotinaEntity rotinaEncontrada = rotinaDataStoreRepository.findByCodigo(codigoRotina);
        if (Objects.isNull(rotinaEncontrada)) {
            throw new RotinaNaoCadastradaException("Não existe uma rotina com o código: " + codigoRotina);
        }
        return rotinaMapper.paraDominio(rotinaEncontrada);
    }

    @Override
    public void associarRotina(Pai pai, Rotina rotina) throws RotinaNaoCadastradaException {
        RotinaEntity rotinaSalva = rotinaDataStoreRepository.save(rotinaMapper.paraEntidade(rotina));
        PaiEntity paiEntity = paiDataStoreRepository.findByEmail(pai.getEmail().getEndereco());
        paiEntity.addicionaRotina(rotinaSalva.getId());
        paiDataStoreRepository.save(paiEntity);
    }

    @Override
    public void registrarParticipacao(Rotina rotina) {
        rotinaDataStoreRepository.save(rotinaMapper.paraEntidade(rotina));
    }

    @Override
    public List<Rotina> buscarRotinasPorTipo(TipoRotina tipoRotina) {
        List<RotinaEntity> rotinasEncontradadas = rotinaDataStoreRepository.findByTipoRotina(tipoRotina.name());
        return rotinasEncontradadas.stream().map(rotinaMapper::paraDominio).collect(Collectors.toList());
    }

    @Override
    public void remover(UUID codigoRotina) {
        rotinaDataStoreRepository.deleteByCodigo(codigoRotina);
    }
}
