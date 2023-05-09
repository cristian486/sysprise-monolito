package br.com.sysprise.infra.security;

import br.com.sysprise.model.funcionario.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class JWTUtils {

    @Value("${sysprise.security.jwt.secret}")
    private String jwtSecret;
    private Algorithm algorithm;

    public String gerarTokenJWT(Usuario usuario) {
        algorithm = Algorithm.HMAC256(jwtSecret);
        return JWT.create()
                    .withIssuer("Sysprise")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(this.calcularDataDeExpiracaoDoToken())
                    .sign(algorithm);
    }

    public String verificarTokenJWT(String token) {
        algorithm = Algorithm.HMAC256(jwtSecret);
        return JWT.require(algorithm)
                    .withIssuer("Sysprise")
                    .build()
                    .verify(token)
                    .getSubject();
    }

    private Instant calcularDataDeExpiracaoDoToken() {
        return LocalDateTime.now().plusHours(3l).toInstant(ZoneOffset.of("-03:00"));
    }
}
