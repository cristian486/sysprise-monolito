package br.com.sysprise.controller;

import br.com.sysprise.model.produto.DadosAtualizarProduto;
import br.com.sysprise.model.produto.DadosCadastroProduto;
import br.com.sysprise.model.produto.DadosDetalhamentoProduto;
import br.com.sysprise.model.produto.DadosListagemProduto;
import br.com.sysprise.service.ProdutoService;
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
@RequestMapping("/produto")
@AllArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<Page<DadosListagemProduto>> listar(@PageableDefault(sort = "id", size = 5) Pageable pageable) {
        Page<DadosListagemProduto> dadosListagem = produtoService.listar(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(dadosListagem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoProduto> detalhar(@PathVariable("id") Long id) {
        DadosDetalhamentoProduto dadosDetalhamento = produtoService.detalhar(id);
        return ResponseEntity.status(HttpStatus.OK).body(dadosDetalhamento);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoProduto> cadastrar(@RequestBody @Valid DadosCadastroProduto dadosCadastro, UriComponentsBuilder componentsBuilder) {
        DadosDetalhamentoProduto dadosDetalhamento = produtoService.cadastrar(dadosCadastro);
        URI uri = componentsBuilder.path("/produto/{id}").buildAndExpand(dadosDetalhamento.id()).toUri();
        return ResponseEntity.created(uri).body(dadosDetalhamento);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoProduto> atualizar(@RequestBody @Valid DadosAtualizarProduto dadosAtualizar) {
        DadosDetalhamentoProduto dadosDetalhamento = produtoService.atualizar(dadosAtualizar);
        return ResponseEntity.status(HttpStatus.OK).body(dadosDetalhamento);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable("id") Long id) {
        produtoService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
