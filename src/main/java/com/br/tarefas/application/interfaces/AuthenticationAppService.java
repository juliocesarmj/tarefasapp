package com.br.tarefas.application.interfaces;

import com.br.tarefas.application.dtos.AuthRequestDto;
import com.br.tarefas.application.dtos.AuthResponseDto;

public interface AuthenticationAppService {

    AuthResponseDto autenticar(AuthRequestDto dto);
}
