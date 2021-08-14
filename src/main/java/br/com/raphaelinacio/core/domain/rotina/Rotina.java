package br.com.raphaelinacio.core.domain.rotina;

import br.com.raphaelinacio.core.domain.pai.Filho;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Rotina {
    private Recorrencia recorrencia;
    private Atividade atividade;
    private Filho filho;

    public List<Historico> verHistoricoDeParticipacoes() {
        return historicoDeParticipacoes;
    }

    private List<Historico> historicoDeParticipacoes = new ArrayList<>();

    public Rotina(Recorrencia recorrencia, Atividade atividade, Filho filho) {
        this.recorrencia = recorrencia;
        this.atividade = atividade;
        this.filho = filho;
    }

    private static boolean temMesmaData(Historico historico) {
        var hoje = LocalDateTime.now();
        if (hoje.truncatedTo(ChronoUnit.DAYS).isEqual(historico.momento.truncatedTo(ChronoUnit.DAYS))) {
            return true;
        }
        return false;
    }

    public void registrarParticipacaoDiaria() {
        this.historicoDeParticipacoes.stream().filter(Rotina::temMesmaData).findFirst().ifPresent(historico -> {
            throw new IllegalStateException("Já existe um registro para a data de hoje" + historico.momento.format(DateTimeFormatter.BASIC_ISO_DATE));
        });
        historicoDeParticipacoes.add(new Historico());
    }

}
