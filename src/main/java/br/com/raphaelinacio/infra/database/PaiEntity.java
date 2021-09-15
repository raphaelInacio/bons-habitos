package br.com.raphaelinacio.infra.database;

import lombok.Data;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Pais")
@Data
public class PaiEntity {

    @Id
    private Long id;

    private String email;

    private String nome;

    private List<FilhoEntity> filhos;

    private List<Long> rotinas = new ArrayList<>();

    public void addicionaRotina(Long idRotina) {
        rotinas.add(idRotina);
    }


}
