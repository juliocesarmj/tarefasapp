package com.tarefas.application.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarefas.application.dtos.authentication.AuthGetDTO;
import com.tarefas.application.dtos.authentication.AuthPostDTO;
import com.tarefas.application.dtos.usuario.UsuarioDTO;
import com.tarefas.application.dtos.usuario.UsuarioPostDTO;
import com.tarefas.domain.services.usuario.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/v1/usuario")
@RequiredArgsConstructor
public class UsuarioController {
	
	private final UsuarioService service;
	
	@PostMapping(path = "/novo")
	public ResponseEntity<UsuarioDTO> novo(@RequestBody final UsuarioPostDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.novoUsuario(dto));
	}
	
	@PostMapping(path = "/auth")
	public ResponseEntity<AuthGetDTO> auth(@RequestBody final AuthPostDTO dto) {
		return ResponseEntity.status(HttpStatus.OK).body(service.auth(dto));
	}
}
