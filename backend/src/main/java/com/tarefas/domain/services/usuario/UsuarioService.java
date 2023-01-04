package com.tarefas.domain.services.usuario;

import com.tarefas.application.dtos.AuthGetDTO;
import com.tarefas.application.dtos.AuthPostDTO;
import com.tarefas.application.dtos.UsuarioDTO;
import com.tarefas.application.dtos.UsuarioPostDTO;

public interface UsuarioService {
	
	UsuarioDTO novoUsuario(UsuarioPostDTO dto);
	
	AuthGetDTO auth(AuthPostDTO dto);
}
