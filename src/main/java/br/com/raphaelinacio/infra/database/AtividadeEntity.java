package br.com.raphaelinacio.infra.database;

import lombok.Data;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;

@Data
@Entity(name = "Atividade")
public class AtividadeEntity {
    private String nome;
    private String titulo;
    private String descricao;
}
