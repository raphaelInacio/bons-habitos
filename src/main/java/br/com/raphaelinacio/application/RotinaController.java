package br.com.raphaelinacio.application;

import br.com.raphaelinacio.core.domain.rotina.RotinaDTO;
import br.com.raphaelinacio.core.usecase.rotina.ConsultarRotinasDoSistema;
import br.com.raphaelinacio.core.usecase.rotina.CriarRotinaSistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/rotinas")
public class RotinaController {

    @Autowired
    private CriarRotinaSistema criarRotinaSistema;

    @Autowired
    private ConsultarRotinasDoSistema consultarRotinasDoSistema;

    @PostMapping
    public ResponseEntity<Void> criaRotinaDeSistema(@RequestBody RotinaDTO rotinaDTO) {
        criarRotinaSistema.executar(rotinaDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping
    ResponseEntity<List<RotinaDTO>> listarRotinasDoSistema() {
        List<RotinaDTO> rotinasDoSistema = consultarRotinasDoSistema.executar();
        return ResponseEntity.ok(rotinasDoSistema);
    }

    @GetMapping("/{codigoRotina}")
    ResponseEntity<RotinaDTO> buscarRotinaPorCodigo(@PathVariable("codigoRotina") String codigoRotina) {
        RotinaDTO rotinaDTO = consultarRotinasDoSistema.executar(codigoRotina);
        return ResponseEntity.ok(rotinaDTO);
    }
}
