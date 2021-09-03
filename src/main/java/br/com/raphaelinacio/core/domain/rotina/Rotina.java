package br.com.raphaelinacio.core.domain.rotina;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Rotina {
    private UUID codigo;
    private Recorrencia recorrencia;
    private Atividade atividade;
    private TipoRotina tipoRotina;
    private List<Historico> historicoDeParticipacoes = new ArrayList<>();

    public List<Historico> verHistoricoDeParticipacoes() {
        return historicoDeParticipacoes;
    }

    public Rotina(Recorrencia recorrencia, Atividade atividade) {
        this.recorrencia = recorrencia;
        this.atividade = atividade;
        this.codigo = UUID.randomUUID();
        this.tipoRotina = TipoRotina.SISTEMA;
    }

    public Rotina(Recorrencia recorrencia, Atividade atividade, TipoRotina tipoRotina) {
        if (TipoRotina.SISTEMA.equals(tipoRotina)) {
            throw new IllegalArgumentException("Pais não podem criar rotinas de sistema");
        }
        this.recorrencia = recorrencia;
        this.atividade = atividade;
        this.codigo = UUID.randomUUID();
        this.tipoRotina = TipoRotina.PAI;
    }

    private static boolean temMesmaData(Historico historico) {
        var hoje = LocalDateTime.now();
        if (hoje.truncatedTo(ChronoUnit.DAYS).isEqual(historico.momento.truncatedTo(ChronoUnit.DAYS))) {
            return true;
        }
        return false;
    }

    public void registrarParticipacaoDiaria() {
        this.historicoDeParticipacoes.stream()
                .filter(Rotina::temMesmaData)
                .findFirst()
                .ifPresent(historico -> {
                    throw new IllegalStateException("Já existe um registro para a data de hoje" + historico.momento.format(DateTimeFormatter.BASIC_ISO_DATE));
                });
        historicoDeParticipacoes.add(new Historico());
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public Recorrencia getRecorrencia() {
        return recorrencia;
    }

    public TipoRotina getTipoRotina() {
        return tipoRotina;
    }

    public UUID getCodigo() {
        return codigo;
    }
}
