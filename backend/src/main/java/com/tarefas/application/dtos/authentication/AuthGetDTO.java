package com.tarefas.application.dtos.authentication;

import com.tarefas.application.dtos.usuario.UsuarioDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthGetDTO extends UsuarioDTO {
	
	private String accessToken;

	public AuthGetDTO(String nome, String email, String accessToken) {
		super(nome, email);
		this.accessToken = accessToken;
	}
}
