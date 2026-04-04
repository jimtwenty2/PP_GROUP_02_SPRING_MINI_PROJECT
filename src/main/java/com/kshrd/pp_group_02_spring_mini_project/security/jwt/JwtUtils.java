package com.kshrd.pp_group_02_spring_mini_project.security.jwt;

import com.kshrd.pp_group_02_spring_mini_project.model.entity.AppUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Component
public class JwtUtils {
    private final long EXPIRATION_TIME = TimeUnit.MINUTES.toMillis(60);
    private final String SECRET_KEY = "mZnbbC0u79OfLNLCxOkolZpnyiwwHdNtZq9ELznHosY=";
    public String generateToken(String identifier) {
        return Jwts.builder()
                .subject(identifier.trim())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSignKey())
                .compact();
    }
    private SecretKey getSignKey() {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    private <T> T extractClaim(String token, Function<Claims, T> resolver) {
        return resolver.apply(extractAllClaims(token));
    }
    public String extractIdentifier(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String identifier = extractIdentifier(token);
        AppUser user = (AppUser) userDetails;
        boolean isMatch = identifier.equals(user.getUsername()) ||
                identifier.equals(user.getEmail());
        return isMatch && !isTokenExpired(token);
    }
}