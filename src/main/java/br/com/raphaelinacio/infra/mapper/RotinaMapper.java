package br.com.raphaelinacio.infra.mapper;

import br.com.raphaelinacio.core.domain.rotina.*;
import br.com.raphaelinacio.infra.database.AtividadeEntity;
import br.com.raphaelinacio.infra.database.RotinaEntity;
import org.springframework.stereotype.Component;


@Component
public class RotinaMapper {

    public RotinaEntity paraEntidade(Rotina rotina) {
        RotinaEntity entidadeRotina = new RotinaEntity();
        entidadeRotina.setCodigo(rotina.getCodigo());
        entidadeRotina.setTipoRotina(rotina.getTipoRotina().name());
        entidadeRotina.setHorarioRecorrencia(rotina.getRecorrencia().getHorarioRecorrencia().getLocalTime());
        entidadeRotina.setTipoRecorrencia(rotina.getRecorrencia().getTipoRecorrencia().name());
        entidadeRotina.setAtividade(paraEntidade(rotina.getAtividade()));
        entidadeRotina.setDataDeInicio(rotina.getRecorrencia().getDataDeInicio());
        return entidadeRotina;
    }

    private AtividadeEntity paraEntidade(Atividade atividade) {
        AtividadeEntity entidadeAtividade = new AtividadeEntity();
        entidadeAtividade.setNome(atividade.getNome());
        entidadeAtividade.setDescricao(atividade.getDescricao());
        entidadeAtividade.setTitulo(atividade.getTitulo());
        return entidadeAtividade;
    }

    public Rotina paraDominio(RotinaEntity entidadeRotina) {

        Recorrencia recorrencia = new Recorrencia(
                TipoRecorrenciaEnum.valueOf(entidadeRotina.getTipoRecorrencia()),
                new HorarioRecorrencia(entidadeRotina.getHorarioRecorrencia()),
                entidadeRotina.getDataDeInicio());

        AtividadeEntity atividadeEntity = entidadeRotina.getAtividade();

        Atividade atividade = new Atividade(
                atividadeEntity.getNome(),
                atividadeEntity.getTitulo(),
                atividadeEntity.getDescricao());

        if (TipoRotina.SISTEMA.name().equals(entidadeRotina.getTipoRotina())) {
            return new Rotina(recorrencia, atividade);
        }

        return new Rotina(recorrencia, atividade, TipoRotina.valueOf(entidadeRotina.getTipoRotina()));
    }
}
