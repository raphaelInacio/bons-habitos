package br.com.raphaelinacio.infra.mapper;

import br.com.raphaelinacio.core.domain.pai.Email;
import br.com.raphaelinacio.infra.database.Filho;
import br.com.raphaelinacio.infra.database.Pai;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaiMapper {

    public Pai paraEntidade(br.com.raphaelinacio.core.domain.pai.Pai pai) {

        Pai entidadePai = new Pai();
        entidadePai.setEmail(pai.getEmail().getEndereco());
        entidadePai.setNome(pai.getNome());

        if (!pai.meusFilhos().isEmpty()) {
            List<Filho> filhos = pai.meusFilhos()
                    .stream().map(filho -> paraEntidade(filho))
                    .collect(Collectors.toList());

            entidadePai.setFilhos(filhos);
        }

        return entidadePai;
    }

    private Filho paraEntidade(br.com.raphaelinacio.core.domain.pai.Filho filho) {
        Filho entidadeFilho = new Filho();
        entidadeFilho.setNome(filho.getNome());
        entidadeFilho.setDataNacimento(filho.getDataDeNascimento());
        return entidadeFilho;
    }

    public br.com.raphaelinacio.core.domain.pai.Pai paraDominio(Pai pai) {
        var dominioPai = new br.com.raphaelinacio.core.domain.pai.Pai(pai.getNome(), new Email(pai.getEmail()));
        pai.getFilhos().stream().forEach(filho -> dominioPai.incluirFilho(paraDominio(filho)));
        return dominioPai;
    }

    private br.com.raphaelinacio.core.domain.pai.Filho paraDominio(Filho filho) {
        return new br.com.raphaelinacio.core.domain.pai.Filho(filho.getNome(), filho.getDataNacimento());
    }
}
