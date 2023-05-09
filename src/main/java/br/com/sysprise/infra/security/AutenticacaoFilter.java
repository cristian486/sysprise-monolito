package br.com.sysprise.infra.security;

import br.com.sysprise.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@AllArgsConstructor
public class AutenticacaoFilter extends OncePerRequestFilter {

    private final JWTUtils jwtUtils;
    private final UsuarioRepository usuarioRepository;
    private final String AUTHORIZATION_HEADER = "Authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional<String> tokenJWT = this.extrairTokenAutenticacao(request);
        tokenJWT.ifPresent(token -> {
            String loginUsuario = jwtUtils.verificarTokenJWT(token);
            UserDetails usuario = usuarioRepository.findUsuarioByLogin(loginUsuario);
            var userToken = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(userToken);
        });
        filterChain.doFilter(request, response);
    }

    private Optional<String> extrairTokenAutenticacao(HttpServletRequest request) {
        if(request.getHeader(AUTHORIZATION_HEADER) == null)
            return Optional.empty();

        String token =  request.getHeader(AUTHORIZATION_HEADER).replace("Bearer ", "");
        return Optional.of(token);
    }
}
