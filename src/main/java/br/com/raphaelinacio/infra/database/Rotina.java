package br.com.raphaelinacio.infra.database;

import lombok.Data;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

@Entity(name = "rotinas")
@Data
public class Rotina {
    @Id
    private Long id;
}
