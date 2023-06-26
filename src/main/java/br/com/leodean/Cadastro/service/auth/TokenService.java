package br.com.leodean.Cadastro.service.auth;


import br.com.leodean.Cadastro.domain.databaseDomain.UserDataBase;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    public String gerarToken(UserDataBase userResponse) {

        return JWT.create()
                .withIssuer("Cadastro")
                .withSubject(userResponse.getEmail())
                .withClaim("id", userResponse.getId())
                .withExpiresAt(LocalDateTime.now().plusMinutes(30)
                        .toInstant(ZoneOffset.of("-03:00"))

                ).sign(Algorithm.HMAC256("secreta"));
    }

    public String getSubject(String token) {

        return JWT.require(Algorithm.HMAC256("secreta"))
                .withIssuer("Cadastro")
                .build().verify(token).getSubject();
    }
}
