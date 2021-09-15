package br.com.raphaelinacio.infra.repository.datastore;

import br.com.raphaelinacio.infra.database.PaiEntity;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaiDataStoreRepository extends DatastoreRepository<PaiEntity, Long> {
    PaiEntity findByEmail(String email);

    void deleteByEmail(String email);
}
