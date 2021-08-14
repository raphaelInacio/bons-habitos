package br.com.raphaelinacio.core.domain.rotina;

import java.time.LocalDate;
import java.util.Objects;

public class Recorrencia {
    private TipoRecorrenciaEnum tipoRecorrencia;
    private HorarioRecorrencia horarioRecorrencia;
    private LocalDate dataDeInicio;

    public Recorrencia(TipoRecorrenciaEnum tipoRecorrencia, HorarioRecorrencia horarioRecorrencia, LocalDate dataDeInicio) {
        this.dataDeInicio = dataDeInicio;
        if (Objects.isNull(tipoRecorrencia) || Objects.isNull(horarioRecorrencia) || Objects.isNull(dataDeInicio)) {
            throw new IllegalArgumentException("Campos obrigatórios não informados");
        }
        this.tipoRecorrencia = tipoRecorrencia;
        this.horarioRecorrencia = horarioRecorrencia;
        this.dataDeInicio = dataDeInicio;
    }
}
