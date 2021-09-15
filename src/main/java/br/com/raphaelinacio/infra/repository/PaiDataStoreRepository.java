package br.com.raphaelinacio.infra.repository;

import br.com.raphaelinacio.infra.database.Pai;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaiDataStoreRepository extends DatastoreRepository<Pai, Long> {
    Pai findByEmail(String email);

    void deleteByEmail(String email);
}
