package com.br.tarefas.domain.service.interfaces;

import com.br.tarefas.domain.model.Usuario;

public interface UsuarioService {

    void criar(Usuario usuario);

    void atualizar(Usuario usuario);
}
