package com.example.demo.service;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.example.demo.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtUtil {
 private final String SECRET = "secret_key";
 private final long EXPIRATION = 1000 * 60 * 60;

 public String generateToken(User user) {
     String token = Jwts.builder()
         .setSubject(user.getEmail())
         .claim("role", user.getRole().name())
         .setIssuedAt(new Date())
         .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
         .signWith(SignatureAlgorithm.HS256, SECRET)
         .compact();
     
     return token;
     
 }
 

 public Claims extractClaims(String token) {
     return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
 }

 public String extractEmail(String token) {
     return extractClaims(token).getSubject();
 }

 public String extractRole(String token) {
     return extractClaims(token).get("role", String.class);
 }
}

