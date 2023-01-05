package com.tarefas.domain.services.auth;

import com.tarefas.domain.model.Usuario;
import com.tarefas.infrastructure.repositories.UsuarioRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public Usuario authenticated(HttpServletRequest request) {
        String accessToken = request.getHeader("Authorization").replace("Bearer", "").trim();
        String emailToken = getContentFromToken(accessToken, Claims::getSubject);

        return getUsuario(emailToken);
    }

    // método para ler o conteudo do TOKEN
    private <T> T getContentFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser().setSigningKey(secret.getBytes())
                .parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }

    private Usuario getUsuario(String email) {
        return this.usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado"));
    }
}
