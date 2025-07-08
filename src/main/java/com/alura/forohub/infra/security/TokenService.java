package com.alura.forohub.infra.security;

import com.alura.forohub.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generarToken(Usuario usuario){
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API forohub")
                    .withSubject(usuario.getNombre())
                    .withExpiresAt(fechaExpiracion())
                    .sign(algoritmo);
        }catch (JWTCreationException exception){
            // Invalid Signing configuration / Couldn't convert Claims.
            throw new RuntimeException("Error al generar el token JWT", exception);
        }
    }

    private Instant fechaExpiracion() { return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-06:00"));}

    public String getSubjetc(String tokenJWT){
        try {
            var algotimo = Algorithm.HMAC256(secret);
            return  JWT.require(algotimo)
                    // specify any specific claim validations
                    .withIssuer("API forohub")
                    // reusable verifier instance
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (JWTVerificationException exception){
            // Invalid signature/claims
            throw new RuntimeException("Token JWT invalido o expirado");
        }
    }
}
