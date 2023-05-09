package br.com.sysprise.controller;

import br.com.sysprise.model.tipo.DadosAtualizarTipo;
import br.com.sysprise.model.tipo.DadosCadastroTipo;
import br.com.sysprise.model.tipo.DadosDetalhamentoTipo;
import br.com.sysprise.model.tipo.DadosListagemTipo;
import br.com.sysprise.service.TipoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/tipo")
@AllArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class TipoSerivce {

    private final TipoService tipoService;

    @GetMapping
    public ResponseEntity<Page<DadosListagemTipo>> listar(@PageableDefault(sort = "id", size = 5) Pageable pageable) {
        Page<DadosListagemTipo> dadosListagem = tipoService.listar(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(dadosListagem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoTipo> detalhar(@PathVariable("id") Long id) {
        DadosDetalhamentoTipo dadosDetalhamento = tipoService.detalhar(id);
        return ResponseEntity.status(HttpStatus.OK).body(dadosDetalhamento);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoTipo> cadastrar(@RequestBody @Valid DadosCadastroTipo dadosCadastro, UriComponentsBuilder componentsBuilder) {
        DadosDetalhamentoTipo dadosDetalhamento = tipoService.cadastrar(dadosCadastro);
        URI uri = componentsBuilder.path("/tipo/{id}").buildAndExpand(dadosDetalhamento.id()).toUri();
        return ResponseEntity.created(uri).body(dadosDetalhamento);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoTipo> atualizar(@RequestBody @Valid DadosAtualizarTipo dadosAtualizar) {
        DadosDetalhamentoTipo dadosDetalhamento = tipoService.atualizar(dadosAtualizar);
        return ResponseEntity.status(HttpStatus.OK).body(dadosDetalhamento);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
        tipoService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
