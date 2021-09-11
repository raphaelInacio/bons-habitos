package br.com.raphaelinacio.infra.repository;

import br.com.raphaelinacio.infra.database.Rotina;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RotinaRepositoryImpl extends DatastoreRepository<Rotina, Long> {
}
