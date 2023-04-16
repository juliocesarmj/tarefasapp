package com.br.tarefas.application.service;

import com.br.tarefas.application.dtos.AuthRequestDto;
import com.br.tarefas.application.dtos.AuthResponseDto;
import com.br.tarefas.application.interfaces.AuthenticationAppService;
import com.br.tarefas.domain.model.Usuario;
import com.br.tarefas.domain.service.interfaces.AuthenticationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationAppServiceImpl implements AuthenticationAppService {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public AuthResponseDto autenticar(AuthRequestDto dto) {

        Usuario usuario = authenticationService.autenticar(dto.getEmail(), dto.getSenha());
        AuthResponseDto responseDto = modelMapper.map(usuario, AuthResponseDto.class);
        responseDto.setMensagem("Usuário autenticado com sucesso");
        return responseDto;
    }
}
