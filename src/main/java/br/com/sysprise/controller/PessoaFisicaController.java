package br.com.sysprise.controller;

import br.com.sysprise.model.pessoa.fisica.DadosAtualizarPessoaFisica;
import br.com.sysprise.model.pessoa.fisica.DadosCadastroPessoaFisica;
import br.com.sysprise.model.pessoa.fisica.DadosDetalhamentoPessoaFisica;
import br.com.sysprise.model.pessoa.fisica.DadosListagemPessoaFisica;
import br.com.sysprise.service.PessoaFisicaService;
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
@RequestMapping("/pessoafisica")
@AllArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class PessoaFisicaController {

    private final PessoaFisicaService pessoaFisicaService;

    @GetMapping
    public ResponseEntity<Page<DadosListagemPessoaFisica>> listar(@PageableDefault(sort = "id", size = 5) Pageable pageable) {
        Page<DadosListagemPessoaFisica> dadosListagem = pessoaFisicaService.listar(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(dadosListagem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPessoaFisica> detalhar(@PathVariable("id") Long id) {
        DadosDetalhamentoPessoaFisica dadosDetalhar = pessoaFisicaService.detalhar(id);
        return ResponseEntity.status(HttpStatus.OK).body(dadosDetalhar);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPessoaFisica> cadastrar(@RequestBody @Valid DadosCadastroPessoaFisica dadosCadastro, UriComponentsBuilder componentsBuilder) {
        DadosDetalhamentoPessoaFisica dadosDetalhar = pessoaFisicaService.cadastrar(dadosCadastro);
        URI uri = componentsBuilder.path("/pessoafisica/{id}").buildAndExpand(dadosDetalhar.getId()).toUri();
        return ResponseEntity.created(uri).body(dadosDetalhar);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPessoaFisica> atualizar(@RequestBody @Valid DadosAtualizarPessoaFisica dadosAtualizar) {
        DadosDetalhamentoPessoaFisica dadosDetalhar = pessoaFisicaService.atualizar(dadosAtualizar);
        return ResponseEntity.status(HttpStatus.OK).body(dadosDetalhar);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
        pessoaFisicaService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
