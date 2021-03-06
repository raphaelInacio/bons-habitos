package br.com.raphaelinacio.core.domain.rotina;

import java.time.LocalTime;
import java.util.Objects;

public class HorarioRecorrencia {
    private LocalTime localTime;

    public HorarioRecorrencia(LocalTime localTime) {
        if (Objects.isNull(localTime)) {
            throw new IllegalArgumentException("Horario de recorrencia invalido");
        }
        this.localTime = localTime;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

}
