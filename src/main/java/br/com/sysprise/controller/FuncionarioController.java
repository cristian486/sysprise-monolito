package br.com.sysprise.controller;

import br.com.sysprise.model.funcionario.DadosAtualizarFuncionario;
import br.com.sysprise.model.funcionario.DadosCadastroFuncionario;
import br.com.sysprise.model.funcionario.DadosDetalhamentoFuncionario;
import br.com.sysprise.model.funcionario.DadosListagemFuncionario;
import br.com.sysprise.service.FuncionarioService;
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
@RequestMapping("/funcionario")
@AllArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @GetMapping
    public ResponseEntity<Page<DadosListagemFuncionario>> listar(@PageableDefault(sort = "id", size = 5) Pageable pageable) {
        Page<DadosListagemFuncionario> dadosListagem = funcionarioService.listar(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(dadosListagem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoFuncionario> detalhar(@PathVariable("id") Long id) {
        DadosDetalhamentoFuncionario dadosDetalhamento = funcionarioService.detalhar(id);
        return ResponseEntity.status(HttpStatus.OK).body(dadosDetalhamento);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoFuncionario> cadastrar(@RequestBody @Valid DadosCadastroFuncionario dadosCadastro, UriComponentsBuilder componentsBuilder) {
        DadosDetalhamentoFuncionario dadosDetalhamento = funcionarioService.cadastrar(dadosCadastro);
        URI uri = componentsBuilder.path("/funcionario/{id}").buildAndExpand(dadosDetalhamento.id()).toUri();
        return ResponseEntity.created(uri).body(dadosDetalhamento);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoFuncionario> atualizar(@RequestBody @Valid DadosAtualizarFuncionario dadosAtualizar) {
        DadosDetalhamentoFuncionario dadosDetalhamento = funcionarioService.atualizar(dadosAtualizar);
        return ResponseEntity.status(HttpStatus.OK).body(dadosDetalhamento);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
       funcionarioService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
