package br.com.sysprise.controller;

import br.com.sysprise.model.categoria.DadosAtualizarCategoria;
import br.com.sysprise.model.categoria.DadosCadastroCategoria;
import br.com.sysprise.model.categoria.DadosDetalhamentoCategoria;
import br.com.sysprise.model.categoria.DadosListagemCategoria;
import br.com.sysprise.service.CategoriaService;
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
@RequestMapping("/categoria")
@AllArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<Page<DadosListagemCategoria>> listar(@PageableDefault(sort = "id", size = 5) Pageable pageable) {
        Page<DadosListagemCategoria> dadosListagem = categoriaService.listar(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(dadosListagem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoCategoria> detalhar(@PathVariable("id") Long id) {
        DadosDetalhamentoCategoria dadosDetalhamento = categoriaService.detalhar(id);
        return ResponseEntity.status(HttpStatus.OK).body(dadosDetalhamento);
    }


    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoCategoria> cadastrar(@RequestBody @Valid DadosCadastroCategoria dadosCadastro, UriComponentsBuilder componentsBuilder) {
        DadosDetalhamentoCategoria dadosDetalhamento = categoriaService.cadastrar(dadosCadastro);
        URI uri = componentsBuilder.path("/categoria/{id}").buildAndExpand(dadosDetalhamento.id()).toUri();
        return ResponseEntity.created(uri).body(dadosDetalhamento);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoCategoria> atualizar(@RequestBody @Valid DadosAtualizarCategoria dadosAtualizar) {
        DadosDetalhamentoCategoria dadosDetalhamento = categoriaService.atualizar(dadosAtualizar);
        return ResponseEntity.status(HttpStatus.OK).body(dadosDetalhamento);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable("id") Long id) {
        categoriaService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
