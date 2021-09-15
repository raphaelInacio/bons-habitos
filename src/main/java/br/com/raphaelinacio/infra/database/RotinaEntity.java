package br.com.raphaelinacio.infra.database;

import lombok.Data;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity(name = "Rotinas")
@Data
public class RotinaEntity {
    @Id
    private Long id;
    private AtividadeEntity atividade;
    private String tipoRecorrencia;
    private LocalTime horarioRecorrencia;
    private LocalDate dataDeInicio;
    private String tipoRotina;
    private UUID codigo;

}
