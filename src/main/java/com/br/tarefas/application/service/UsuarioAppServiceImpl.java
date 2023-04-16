package com.br.tarefas.application.service;

import com.br.tarefas.application.dtos.UsuarioPostDTO;
import com.br.tarefas.application.dtos.UsuarioPutDto;
import com.br.tarefas.application.dtos.UsuarioResponseSemIdDTO;
import com.br.tarefas.application.interfaces.UsuarioAppService;
import com.br.tarefas.domain.model.Usuario;
import com.br.tarefas.domain.service.interfaces.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioAppServiceImpl implements UsuarioAppService {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public UsuarioResponseSemIdDTO criarConta(UsuarioPostDTO dto) {
        Usuario usuario = modelMapper.map(dto, Usuario.class);
        usuarioService.criar(usuario);
        UsuarioResponseSemIdDTO responseDto = modelMapper.map(usuario, UsuarioResponseSemIdDTO.class);
        responseDto.setMensagem("Cadastro realizado com sucesso!");
        return responseDto;
    }

    @Override
    public UsuarioResponseSemIdDTO atualizarDados(UsuarioPutDto dto, String emailToken) {
        Usuario usuario = modelMapper.map(dto, Usuario.class);
        usuario.setEmail(emailToken);
        usuarioService.atualizar(usuario);
        UsuarioResponseSemIdDTO responseDto = modelMapper.map(usuario, UsuarioResponseSemIdDTO.class);
        responseDto.setMensagem("Dados atualizados com sucesso");
        return responseDto;
    }
}

