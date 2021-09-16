package br.com.raphaelinacio.infra.repository.datastore;

import br.com.raphaelinacio.infra.database.RotinaEntity;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RotinaDataStoreRepository extends DatastoreRepository<RotinaEntity, Long> {
    RotinaEntity findByCodigo(UUID codigo);

    List<RotinaEntity> findByTipoRotina(String tipoRotina);

    void deleteByCodigo(UUID codigo);
}
