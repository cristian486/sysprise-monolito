package br.com.sysprise.controller;

import br.com.sysprise.model.cidade.DadosAtualizarCidade;
import br.com.sysprise.model.cidade.DadosCadastroCidade;
import br.com.sysprise.model.cidade.DadosDetalhamentoCidade;
import br.com.sysprise.model.cidade.DadosListagemCidade;
import br.com.sysprise.service.CidadeService;
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
@RequestMapping("/cidade")
@AllArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class CidadeController {

    private final CidadeService cidadeService;

    @GetMapping
    public ResponseEntity<Page<DadosListagemCidade>> listar(@PageableDefault(sort = "id", size = 5) Pageable pageable) {
        Page<DadosListagemCidade> dadosListagem = cidadeService.listar(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(dadosListagem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoCidade> detalhar(@PathVariable("id") Long id) {
        DadosDetalhamentoCidade dadosDetalhamento = cidadeService.detalhar(id);
        return ResponseEntity.status(HttpStatus.OK).body(dadosDetalhamento);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoCidade> cadastrar(@RequestBody @Valid DadosCadastroCidade dadosCadastro, UriComponentsBuilder componentsBuilder) {
        DadosDetalhamentoCidade dadosDetalhamento = cidadeService.cadastrar(dadosCadastro);
        URI uri = componentsBuilder.path("/cidade/{id}").buildAndExpand(dadosDetalhamento.id()).toUri();
        return ResponseEntity.created(uri).body(dadosDetalhamento);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoCidade> atualizar(@RequestBody @Valid DadosAtualizarCidade dadosAtualizar) {
        DadosDetalhamentoCidade dadosDetalhamento = cidadeService.atualizar(dadosAtualizar);
        return ResponseEntity.status(HttpStatus.OK).body(dadosDetalhamento);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable("id") Long id) {
        cidadeService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
