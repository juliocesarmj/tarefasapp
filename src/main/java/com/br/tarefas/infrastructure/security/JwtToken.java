package com.br.tarefas.infrastructure.security;

import com.br.tarefas.infrastructure.security.exceptions.SecurityAppException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.core.env.Environment;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.util.Date;

@Data
public class JwtToken {

    private String token;
    private final Claims claims;

    public JwtToken(String token) {
        this.token = token;
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String jwtSecret = "";
        if(attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            WebApplicationContext context = RequestContextUtils.findWebApplicationContext(request);
            if (context != null) {
                Environment environment = context.getEnvironment();
                jwtSecret = environment.getProperty("jwt.secret");
            }
        }
        try {
            this.claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

        } catch (JwtException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Erro ao decifrar token. " + e.getMessage());
        }
    }

    public boolean isValid() {
        Date now = new Date();
        Date expiration = claims.getExpiration();

        return expiration.after(now);
    }

    public String getUsername() {
        return claims.getSubject();
    }
}
