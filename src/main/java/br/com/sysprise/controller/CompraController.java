package br.com.sysprise.controller;

import br.com.sysprise.model.compra.DadosAtualizarCompra;
import br.com.sysprise.model.compra.DadosCadastroCompra;
import br.com.sysprise.model.compra.DadosDetalhamentoCompra;
import br.com.sysprise.model.compra.DadosListagemCompra;
import br.com.sysprise.service.CompraService;
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
@RequestMapping("/compra")
@AllArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class CompraController {

    private final CompraService compraService;

    @GetMapping
    public ResponseEntity<Page<DadosListagemCompra>> listar(@PageableDefault(sort = "id", size = 5) Pageable pageable) {
        Page<DadosListagemCompra> dadosListagem = compraService.listar(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(dadosListagem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoCompra> detalhar(@PathVariable("id") Long id) {
        DadosDetalhamentoCompra dadosDetalhamento = compraService.detalhar(id);
        return ResponseEntity.status(HttpStatus.OK).body(dadosDetalhamento);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoCompra> cadastrar(@RequestBody @Valid DadosCadastroCompra dadosCadastro, UriComponentsBuilder componentsBuilder) {
        DadosDetalhamentoCompra dadosDetalhamento = compraService.cadastrar(dadosCadastro);
        URI uri = componentsBuilder.path("/compra/{id}").buildAndExpand(dadosDetalhamento.id()).toUri();
        return ResponseEntity.created(uri).body(dadosDetalhamento);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoCompra> atualizar(@RequestBody @Valid DadosAtualizarCompra dadosAtualizar) {
        DadosDetalhamentoCompra dadosDetalhamento = compraService.atualizar(dadosAtualizar);
        return ResponseEntity.status(HttpStatus.OK).body(dadosDetalhamento);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable("id") Long id) {
        compraService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
