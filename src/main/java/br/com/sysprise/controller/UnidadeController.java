package br.com.sysprise.controller;

import br.com.sysprise.model.unidade.DadosAtualizarUnidade;
import br.com.sysprise.model.unidade.DadosCadastroUnidade;
import br.com.sysprise.model.unidade.DadosDetalhamentoUnidade;
import br.com.sysprise.model.unidade.DadosListagemUnidade;
import br.com.sysprise.service.UnidadeService;
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
@RequestMapping("/unidade")
@AllArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class UnidadeController {

    private final UnidadeService unidadeService;

    @GetMapping
    public ResponseEntity<Page<DadosListagemUnidade>> listar(@PageableDefault(sort = "id",size = 5) Pageable pageable) {
        Page<DadosListagemUnidade> dadosListagem = unidadeService.listar(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(dadosListagem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoUnidade> detalhar(@PathVariable("id") Long id) {
        DadosDetalhamentoUnidade dadosDetalhamento = unidadeService.detalhar(id);
        return ResponseEntity.status(HttpStatus.OK).body(dadosDetalhamento);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoUnidade> cadastrar(@RequestBody @Valid DadosCadastroUnidade dadosCadastro, UriComponentsBuilder componentsBuilder) {
        DadosDetalhamentoUnidade dadosDetalhamento = unidadeService.cadastrar(dadosCadastro);
        URI uri = componentsBuilder.path("/unidade/{id}").buildAndExpand(dadosDetalhamento.id()).toUri();
        return ResponseEntity.created(uri).body(dadosDetalhamento);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoUnidade> atualizar(@RequestBody @Valid DadosAtualizarUnidade dadosAtualizar) {
        DadosDetalhamentoUnidade dadosDetalhamento = unidadeService.atualizar(dadosAtualizar);
        return ResponseEntity.status(HttpStatus.OK).body(dadosDetalhamento);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable("id") Long id) {
        unidadeService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
