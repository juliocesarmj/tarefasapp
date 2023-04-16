package com.br.tarefas.api.controllers;

import com.br.tarefas.api.constantes.PathEndpoints;
import com.br.tarefas.application.dtos.AuthRequestDto;
import com.br.tarefas.application.dtos.AuthResponseDto;
import com.br.tarefas.application.interfaces.AuthenticationAppService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PathEndpoints.AUTENTICAR)
public class AuthenticationController {

    @Autowired
    AuthenticationAppService authenticationAppService;

    @PostMapping
    public ResponseEntity<AuthResponseDto> autenticar(@RequestBody @Valid AuthRequestDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(authenticationAppService.autenticar(dto));
    }
}
