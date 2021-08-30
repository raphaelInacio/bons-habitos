package br.com.raphaelinacio.application;

import br.com.raphaelinacio.core.domain.pai.CadastroPaiDTO;
import br.com.raphaelinacio.core.domain.pai.Pai;
import br.com.raphaelinacio.core.domain.pai.PaiDTO;
import br.com.raphaelinacio.core.usecase.pai.BuscarMinhasInformacoes;
import br.com.raphaelinacio.core.usecase.pai.CadastrarPaiNoSistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/v1/pais")
public class PaiController {

    @Autowired
    private CadastrarPaiNoSistema cadastrarPaiNoSistema;

    @Autowired
    private BuscarMinhasInformacoes buscarMinhasInformacoes;

    @PostMapping
    public ResponseEntity<Void> cadastrarPaiNoSistema(@RequestBody CadastroPaiDTO cadastroPaiDTO) {
        cadastrarPaiNoSistema.executar(cadastroPaiDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{email}")
    public ResponseEntity<PaiDTO> cadastrarPaiNoSistema(@PathVariable("email") String enderecoEmailPai) {
        PaiDTO paiDTO = buscarMinhasInformacoes.executar(enderecoEmailPai);
        return ResponseEntity.ok(paiDTO);
    }
}



