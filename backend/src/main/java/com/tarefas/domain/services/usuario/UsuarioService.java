package com.tarefas.domain.services.usuario;

import com.tarefas.application.dtos.authentication.AuthGetDTO;
import com.tarefas.application.dtos.authentication.AuthPostDTO;
import com.tarefas.application.dtos.usuario.UsuarioDTO;
import com.tarefas.application.dtos.usuario.UsuarioPostDTO;

public interface UsuarioService {
	
	UsuarioDTO novoUsuario(UsuarioPostDTO dto);
	
	AuthGetDTO auth(AuthPostDTO dto);
}
