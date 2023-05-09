package br.com.sysprise.controller;

import br.com.sysprise.model.pessoa.juridica.DadosAtualizarPessoaJuridica;
import br.com.sysprise.model.pessoa.juridica.DadosCadastroPessoaJuridica;
import br.com.sysprise.model.pessoa.juridica.DadosDetalhamentoPessoaJuridica;
import br.com.sysprise.model.pessoa.juridica.DadosListagemPessoaJuridica;
import br.com.sysprise.service.PessoaJuridicaService;
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
@RequestMapping("/pessoajuridica")
@AllArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class PessoaJuridicaController {

    private final PessoaJuridicaService pessoaJuridicaService;

    @GetMapping
    public ResponseEntity<Page<DadosListagemPessoaJuridica>> listar(@PageableDefault(sort = "id", size = 5) Pageable pageable) {
        Page<DadosListagemPessoaJuridica> dadosListagem = pessoaJuridicaService.listar(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(dadosListagem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPessoaJuridica> detalhar(@PathVariable("id") Long id) {
        DadosDetalhamentoPessoaJuridica dadosDetalhar = pessoaJuridicaService.detalhar(id);
        return ResponseEntity.status(HttpStatus.OK).body(dadosDetalhar);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPessoaJuridica> cadastrar(@RequestBody @Valid DadosCadastroPessoaJuridica dadosCadastro, UriComponentsBuilder componentsBuilder) {
        DadosDetalhamentoPessoaJuridica dadosDetalhar = pessoaJuridicaService.cadastrar(dadosCadastro);
        URI uri = componentsBuilder.path("/pessoajuridica/{id}").buildAndExpand(dadosDetalhar.getId()).toUri();
        return ResponseEntity.created(uri).body(dadosDetalhar);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPessoaJuridica> atualizar(@RequestBody @Valid DadosAtualizarPessoaJuridica dadosAtualizar) {
        DadosDetalhamentoPessoaJuridica dadosDetalhar = pessoaJuridicaService.atualizar(dadosAtualizar);
        return ResponseEntity.status(HttpStatus.OK).body(dadosDetalhar);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
        pessoaJuridicaService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
