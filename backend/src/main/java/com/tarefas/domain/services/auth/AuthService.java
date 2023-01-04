package com.tarefas.domain.services.auth;

import com.tarefas.domain.model.Usuario;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {

    Usuario authenticated(HttpServletRequest request);
}
