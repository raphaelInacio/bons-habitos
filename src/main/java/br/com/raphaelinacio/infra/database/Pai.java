package br.com.raphaelinacio.infra.database;

import lombok.Data;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

import java.util.List;

@Entity(name = "pais")
@Data
public class Pai {

    @Id
    private Long id;

    private String email;

    private String nome;

    private List<Filho> filhos;

    private List<Long> rotinas;
}
