package com.br.tarefas.api.controllers;

import com.br.tarefas.api.constantes.PathEndpoints;
import com.br.tarefas.application.dtos.UsuarioPostDTO;
import com.br.tarefas.application.dtos.UsuarioPutDto;
import com.br.tarefas.application.dtos.UsuarioResponseSemIdDTO;
import com.br.tarefas.application.interfaces.UsuarioAppService;
import com.br.tarefas.infrastructure.security.SecurityEndpoint;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsuarioController extends SecurityEndpoint {

    @Autowired
    UsuarioAppService usuarioAppService;

    @PostMapping(PathEndpoints.CRIAR_CONTA)
    public ResponseEntity<UsuarioResponseSemIdDTO> criarConta(@RequestBody @Valid UsuarioPostDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioAppService.criarConta(dto));
    }

    @PutMapping(PathEndpoints.ATUALIZAR_DADOS)
    public ResponseEntity<UsuarioResponseSemIdDTO> atualizarDados(@RequestHeader("Authorization") String authorizationHeader, @RequestBody @Valid UsuarioPutDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioAppService.atualizarDados(dto, getEmailToken(authorizationHeader)));
    }
}
