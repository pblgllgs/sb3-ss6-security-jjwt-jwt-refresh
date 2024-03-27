package com.pblgllgs.security.services.impl;

import com.pblgllgs.security.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

/*
 *
 * @author pblgl
 * Created on 27-03-2024
 *
 */
@Service
public class JwtServiceImpl implements JwtService {

    SecretKey key = Jwts.SIG.HS256.key().build();

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .issuedAt(new Date(System.currentTimeMillis()))
                .signWith(key)
                .compact();
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsFunction) {
        final Claims claims = extractAllClaims(token);
        return claimsFunction.apply(claims);
    }


    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


    public String refreshToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 604800000))
                .subject(userDetails.getUsername())
                .signWith(key)
                .compact();
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }
}
