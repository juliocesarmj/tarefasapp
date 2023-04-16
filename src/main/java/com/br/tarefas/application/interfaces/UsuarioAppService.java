package com.br.tarefas.application.interfaces;

import com.br.tarefas.application.dtos.UsuarioPostDTO;
import com.br.tarefas.application.dtos.UsuarioPutDto;
import com.br.tarefas.application.dtos.UsuarioResponseSemIdDTO;

public interface UsuarioAppService {

    UsuarioResponseSemIdDTO criarConta(UsuarioPostDTO dto);

    UsuarioResponseSemIdDTO atualizarDados(UsuarioPutDto dto, String emailToken);
}
