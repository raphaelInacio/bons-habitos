package br.com.raphaelinacio.application.controller;

import br.com.raphaelinacio.core.domain.pai.dto.CadastroPaiDTO;
import br.com.raphaelinacio.core.domain.pai.dto.PaiDTO;
import br.com.raphaelinacio.core.domain.rotina.dto.RotinaDTO;
import br.com.raphaelinacio.core.usecase.pai.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/v1/pais")
public class PaiController {

    @Autowired
    private CadastrarPaiNoSistema cadastrarPaiNoSistema;

    @Autowired
    private BuscarMinhasInformacoes buscarMinhasInformacoes;

    @Autowired
    private CriarNovaRotina criarNovaRotina;

    @Autowired
    private RegistrarParticipacao registrarParticipacao;

    @Autowired
    private ConsultarMinhasRotinas consultarMinhasRotinas;

    @PostMapping
    public ResponseEntity<Void> cadastrarPaiNoSistema(@RequestBody CadastroPaiDTO cadastroPaiDTO) {
        cadastrarPaiNoSistema.executar(cadastroPaiDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{email}")
    public ResponseEntity<PaiDTO> buscarMinhasInformacoes(@PathVariable("email") String enderecoEmailPai) {
        PaiDTO paiDTO = buscarMinhasInformacoes.executar(enderecoEmailPai);
        return ResponseEntity.ok(paiDTO);
    }

    @PostMapping("/{email}/rotinas")
    public ResponseEntity<Void> criarNovaRotina(@PathVariable("email") String enderecoEmailPai, @RequestBody RotinaDTO rotinaDTO) {
        criarNovaRotina.executar(enderecoEmailPai, rotinaDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{email}/rotinas/{codigoRotina}")
    public ResponseEntity<Void> registarParticipacao(@PathVariable("email") String enderecoEmailPai, @PathVariable("codigoRotina") UUID codigoRotina) {
        registrarParticipacao.executar(enderecoEmailPai, codigoRotina);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{email}/rotinas")
    public ResponseEntity<List<RotinaDTO>> buscarMinasRotinas(@PathVariable("email") String enderecoEmailPai) {
        List<RotinaDTO> minhasRotinas = consultarMinhasRotinas.executar(enderecoEmailPai);
        return ResponseEntity.ok(minhasRotinas);
    }
}



