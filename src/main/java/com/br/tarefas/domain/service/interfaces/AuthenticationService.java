package com.br.tarefas.domain.service.interfaces;

import com.br.tarefas.domain.model.Usuario;

public interface AuthenticationService {

    Usuario autenticar(String email, String senha);
}
