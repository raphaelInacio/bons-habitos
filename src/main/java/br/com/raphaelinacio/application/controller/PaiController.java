package br.com.raphaelinacio.application.controller;

import br.com.raphaelinacio.core.domain.pai.dto.CadastroPaiDTO;
import br.com.raphaelinacio.core.domain.pai.dto.PaiDTO;
import br.com.raphaelinacio.core.domain.rotina.dto.RotinaDTO;
import br.com.raphaelinacio.core.usecase.pai.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/v1/pais")
@Slf4j
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

    @Autowired
    private IncluirRotinaParaMeuFilho incluirRotinaParaMeuFilho;

    @PostMapping
    public ResponseEntity<Void> cadastrarPaiNoSistema(@RequestBody CadastroPaiDTO cadastroPaiDTO) {
        log.info("..: Cadastrando novo pai no sistema {} ", cadastroPaiDTO.getEnderecoEmailPai());
        cadastrarPaiNoSistema.executar(cadastroPaiDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{email}")
    public ResponseEntity<PaiDTO> buscarMinhasInformacoes(@PathVariable("email") String enderecoEmailPai) {
        log.info("..: Buscando informações sobre o pai {} ", enderecoEmailPai);
        PaiDTO paiDTO = buscarMinhasInformacoes.executar(enderecoEmailPai);
        return ResponseEntity.ok(paiDTO);
    }

    @PostMapping("/{email}/rotinas")
    public ResponseEntity<Void> criarNovaRotina(@PathVariable("email") String enderecoEmailPai, @RequestBody RotinaDTO rotinaDTO) {
        log.info("..: Criando uma nova para o pai {} ", enderecoEmailPai);
        criarNovaRotina.executar(enderecoEmailPai, rotinaDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{email}/rotinas/{codigoRotina}")
    public ResponseEntity<Void> registarParticipacao(@PathVariable("email") String enderecoEmailPai, @PathVariable("codigoRotina") UUID codigoRotina) {
        log.info("..: Registrando participação na rotina {} para o pai  {} ", codigoRotina, enderecoEmailPai);
        registrarParticipacao.executar(enderecoEmailPai, codigoRotina);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{email}/rotinas/{codigoRotina}/incluir")
    public ResponseEntity<Void> incluirRotina(@PathVariable("email") String enderecoEmailPai, @PathVariable("codigoRotina") UUID codigoRotina) {
        log.info("..: Incluindo a rotina {} para o pai  {} ", codigoRotina, enderecoEmailPai);
        incluirRotinaParaMeuFilho.executar(enderecoEmailPai, codigoRotina);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/{email}/rotinas")
    public ResponseEntity<List<RotinaDTO>> buscarMinasRotinas(@PathVariable("email") String enderecoEmailPai) {
        log.info("..: Listando todas as rotinas para o pai {} ", enderecoEmailPai);
        List<RotinaDTO> minhasRotinas = consultarMinhasRotinas.executar(enderecoEmailPai);
        return ResponseEntity.ok(minhasRotinas);
    }
}



