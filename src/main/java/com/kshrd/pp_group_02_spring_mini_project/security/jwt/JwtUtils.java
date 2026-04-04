package com.kshrd.pp_group_02_spring_mini_project.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtils {
    private final long EXPIRATION_TIME = TimeUnit.MINUTES.toMillis(60);
    private final String SECRET_KEY = "mZnbbC0u79OfLNLCxOkolZpnyiwwHdNtZq9ELznHosY=";
    public String generateToken(String identifier){
        return Jwts
                .builder()
                .subject(identifier)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSignKey())
                .compact();
    }

    public SecretKey getSignKey(){
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
