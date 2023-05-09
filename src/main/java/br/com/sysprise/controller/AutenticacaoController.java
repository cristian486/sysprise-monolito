package br.com.sysprise.controller;

import br.com.sysprise.infra.security.JWTUtils;
import br.com.sysprise.model.funcionario.usuario.DadosLogin;
import br.com.sysprise.model.funcionario.usuario.DadosUsuarioAutenticado;
import br.com.sysprise.model.funcionario.usuario.Usuario;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class AutenticacaoController {

    private final JWTUtils jwtUtils;
    private final AuthenticationManager authentication;

    @PostMapping
    public ResponseEntity<DadosUsuarioAutenticado> login(@RequestBody @Valid DadosLogin dadosLogin) {
        var userToken = new UsernamePasswordAuthenticationToken(dadosLogin.login(), dadosLogin.senha());
        Authentication authenticate = authentication.authenticate(userToken);
        Usuario usuario = (Usuario) authenticate.getPrincipal();
        String jwtToken = jwtUtils.gerarTokenJWT(usuario);
        return ResponseEntity.status(HttpStatus.OK).body(new DadosUsuarioAutenticado(jwtToken, usuario.getFuncionario().getNome()));
    }
}
