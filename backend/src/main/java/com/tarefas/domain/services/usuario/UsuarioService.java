package com.tarefas.domain.services.usuario;

import com.tarefas.application.dtos.UsuarioDTO;
import com.tarefas.application.dtos.UsuarioPostDTO;

public interface UsuarioService {
	
	UsuarioDTO novoUsuario(UsuarioPostDTO dto);
}
