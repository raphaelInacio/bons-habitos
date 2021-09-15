package br.com.raphaelinacio.infra.mapper;

import br.com.raphaelinacio.core.domain.pai.Email;
import br.com.raphaelinacio.infra.database.FilhoEntity;
import br.com.raphaelinacio.infra.database.PaiEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaiMapper {

    public PaiEntity paraEntidade(br.com.raphaelinacio.core.domain.pai.Pai pai) {

        PaiEntity entidadePaiEntity = new PaiEntity();
        entidadePaiEntity.setEmail(pai.getEmail().getEndereco());
        entidadePaiEntity.setNome(pai.getNome());

        if (!pai.meusFilhos().isEmpty()) {
            List<FilhoEntity> filhoEntities = pai.meusFilhos()
                    .stream().map(filho -> paraEntidade(filho))
                    .collect(Collectors.toList());

            entidadePaiEntity.setFilhos(filhoEntities);
        }

        return entidadePaiEntity;
    }

    private FilhoEntity paraEntidade(br.com.raphaelinacio.core.domain.pai.Filho filho) {
        FilhoEntity entidadeFilhoEntity = new FilhoEntity();
        entidadeFilhoEntity.setNome(filho.getNome());
        entidadeFilhoEntity.setDataNascimento(filho.getDataDeNascimento());
        return entidadeFilhoEntity;
    }

    public br.com.raphaelinacio.core.domain.pai.Pai paraDominio(PaiEntity paiEntity) {
        var dominioPai = new br.com.raphaelinacio.core.domain.pai.Pai(paiEntity.getNome(), new Email(paiEntity.getEmail()));
        paiEntity.getFilhos().stream().forEach(filhoEntity -> dominioPai.incluirFilho(paraDominio(filhoEntity)));
        return dominioPai;
    }

    private br.com.raphaelinacio.core.domain.pai.Filho paraDominio(FilhoEntity filhoEntity) {
        return new br.com.raphaelinacio.core.domain.pai.Filho(filhoEntity.getNome(), filhoEntity.getDataNascimento());
    }
}
