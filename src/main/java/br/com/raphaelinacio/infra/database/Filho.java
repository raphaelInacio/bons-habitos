package br.com.raphaelinacio.infra.database;

import lombok.Data;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;

import java.time.LocalDate;

@Data
@Entity
public class Filho {
    private String nome;
    private LocalDate dataNacimento;
}
