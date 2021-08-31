package br.com.raphaelinacio.infra;

import br.com.raphaelinacio.core.domain.pai.CadastroPaiException;
import br.com.raphaelinacio.core.domain.pai.PaiNaoCadastradoException;
import br.com.raphaelinacio.core.domain.rotina.RotinaNaoCadastradaException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(value = {PaiNaoCadastradoException.class, RotinaNaoCadastradaException.class})
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Erro recursoNaoEncontradoException(Exception ex, WebRequest request) {
        return new Erro(ex.getMessage(), HttpStatus.NO_CONTENT.value());
    }

    @ExceptionHandler(value = {CadastroPaiException.class})
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public Erro regrasDeNegocioException(Exception ex, WebRequest request) {
        return new Erro(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY.value());
    }
}
