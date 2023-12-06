package com.example.shipnhanh.Jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.AuthenticationManager;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtCreateToken {

    private final AuthenticationManager authenticationManager;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public JwtCreateToken(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    private Authentication authenticate(String phone, String password) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken (phone, password));
    }
    private String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<> ();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date (System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
