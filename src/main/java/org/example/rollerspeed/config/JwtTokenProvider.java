package org.example.rollerspeed.config;

import jakarta.servlet.http.HttpServletRequest;
import org.example.rollerspeed.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String secret = "mySecretKey"; // En producción, usa una clave segura
    private final long validityInMilliseconds = 3600000; // 1 hora
    private final UserService userService;

    public JwtTokenProvider(UserService userService) {
        this.userService = userService;
    }

    public String createToken(String username) {
        String role = userService.getUserRole(username);
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role) // Añadimos el rol al token
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validityInMilliseconds))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public String getRole(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("role", String.class);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
