package br.com.sysprise.controller;

import br.com.sysprise.model.funcionario.usuario.DadosListagemUsuario;
import br.com.sysprise.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@AllArgsConstructor
public class UsuarioControlle {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Page<DadosListagemUsuario>> listar(@PageableDefault(sort = "id", size = 5) Pageable pageable) {
        Page<DadosListagemUsuario> dadosListagem = usuarioService.listar(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(dadosListagem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemUsuario> detalhar(@PathVariable("id") Long id) {
        DadosListagemUsuario dadosListagem = usuarioService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(dadosListagem);
    }
}
