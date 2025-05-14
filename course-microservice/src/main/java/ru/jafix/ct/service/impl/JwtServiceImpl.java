package ru.jafix.ct.service.impl;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.jafix.ct.service.JwtService;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Service
public class JwtServiceImpl implements JwtService {
    @Value("${jwt.secret}")
    private String secret;

    @Override
    public Claims validateAndParseClaims(String jwt) {
        try {
            return Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(jwt)
                    .getPayload();
        } catch (MalformedJwtException e) {
            log.error("Jwt is malformed");
        } catch (ExpiredJwtException e) {
            log.error("Jwt is expired");
        } catch (Exception e) {
            log.error("Jwt is not valid");
        }

        return null;
    }

    private SecretKey getSecretKey() {
        byte[] secretBytes = Base64.getDecoder().decode(secret);
        return Keys.hmacShaKeyFor(secretBytes);
    }
}
