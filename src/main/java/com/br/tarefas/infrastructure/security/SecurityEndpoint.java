package com.br.tarefas.infrastructure.security;

import com.br.tarefas.infrastructure.security.exceptions.SecurityAppException;

public class SecurityEndpoint {

    public String getEmailToken(String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        JwtToken jwtToken = new JwtToken(token);
        if (!jwtToken.isValid()) {
            throw new SecurityAppException("Erro ao decifrar token.");
        }
        return jwtToken.getUsername();
    }
}
