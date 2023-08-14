package com.example.assign.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    String extractUsername(String token);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    Claims extractAllClaims(String token);

    Key getSignInKey();

    String generateToken(UserDetails userDetails);

    String generateToken(Map<String, Object> extracClaims, UserDetails userDetails);

    Boolean isValidToken(String token, UserDetails userDetails);

    Boolean isTokenExpired(String token);

    Date extractExpired(String token);
}
