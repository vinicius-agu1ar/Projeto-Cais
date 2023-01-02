package br.com.compass.cais.config.security.service;

import br.com.compass.cais.entites.User;
import br.com.compass.cais.exceptions.response.GenerateTokenException;
import br.com.compass.cais.exceptions.response.InvalidTokenException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Slf4j
@Service
public class TokenService {
    @Value("${cais.jwt.secret}")
    private String secret;

    public String generateToken(User user) {
        log.info("Gerando token - TokenService");
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API CAIS")
                    .withSubject(user.getEmail())
                    .withExpiresAt(expiration())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new GenerateTokenException();
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("API CAIS")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new InvalidTokenException();
        }
    }

    private Instant expiration() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }


}
