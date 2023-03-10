package com.tarefas.application.dtos.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioPostDTO {
	
	private String nome;
	private String email;
	private String senha;
}
