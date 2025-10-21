package dev.furkankeskin.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JWTService {

    public static final String SECRET_KEY = "PqK8cDowse3o9UwhyP7MFzQAIRjFgfn+1/dFv3eh3tQ=";

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 3600 * 2))
                .signWith(null, SignatureAlgorithm.HS256)
                .compact();
    }

    public <T> T exportTokens(String token, Function<Claims, T> claimsFunc) {
        Claims claims = getClaims(token);
        return claimsFunc.apply(claims);
    }

    public Claims getClaims(String token) {
        Claims claims = Jwts.parser().setSigningKey(getKey())
                .build().parseClaimsJws(token).getBody();
        return claims;
    }

    public String getUsernameByToken(String token) {
        return exportTokens(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token) {
        Date expireDate = exportTokens(token, Claims::getExpiration);
        return new Date().before(expireDate);
    }

    public Key getKey() {
        byte[] bytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }
}
