package br.com.raphaelinacio.core.domain.rotina;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class RotinaDTO {
    private AtividadeDTO atividadeDTO;
    private String tipoRecorrencia;
    private LocalTime horarioRecorrencia;
    private UUID codigo;

    public RotinaDTO(AtividadeDTO atividadeDTO, String tipoRecorrencia, LocalTime horarioRecorrencia, UUID codigo) {
        this.atividadeDTO = atividadeDTO;
        this.tipoRecorrencia = tipoRecorrencia;
        this.horarioRecorrencia = horarioRecorrencia;
        this.codigo = codigo;
    }

    public Rotina converterParaDominio() {

        Atividade atividade = atividadeDTO.converterParaDominio();

        Recorrencia recorrencia = new Recorrencia(
                TipoRecorrenciaEnum.valueOf(tipoRecorrencia),
                new HorarioRecorrencia(horarioRecorrencia),
                LocalDate.now());

        return new Rotina(recorrencia, atividade, TipoRotina.PAI);
    }

    public static List<RotinaDTO> converte(List<Rotina> rotinas) {
        return rotinas.stream()
                .filter(Objects::nonNull)
                .map(rotina -> {
                    Atividade atividade = rotina.getAtividade();
                    Recorrencia recorrencia = rotina.getRecorrencia();
                    AtividadeDTO atividadeDTO = new AtividadeDTO(atividade.getNome(), atividade.getTitulo(), atividade.getDescricao());
                    RotinaDTO rotinaDTO = new RotinaDTO(atividadeDTO, recorrencia.getTipoRecorrencia().name(), recorrencia.getHorarioRecorrencia().getLocalTime(), rotina.getCodigo());
                    return rotinaDTO;
                }).collect(Collectors.toList());
    }

    public AtividadeDTO getAtividadeDTO() {
        return atividadeDTO;
    }

    public String getTipoRecorrencia() {
        return tipoRecorrencia;
    }

    public LocalTime getHorarioRecorrencia() {
        return horarioRecorrencia;
    }

    public UUID getCodigo() {
        return codigo;
    }


}
