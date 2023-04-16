package com.br.tarefas.domain.service.impl;

import com.br.tarefas.domain.model.Usuario;
import com.br.tarefas.domain.service.components.MD5Component;
import com.br.tarefas.domain.service.exceptions.NotFoundException;
import com.br.tarefas.domain.service.interfaces.AuthenticationService;
import com.br.tarefas.infrastructure.repositories.UsuarioRepository;
import com.br.tarefas.infrastructure.security.TokenCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TokenCreator tokenCreator;

    @Autowired
    MD5Component md5Component;

    @Override
    public Usuario autenticar(String email, String senha) {
        Usuario usuario = findByEmailAndSenha(email, md5Component.encrypt(senha));
        usuario.setAccessToken(tokenCreator.generateToken(usuario.getEmail()));
        return usuario;
    }

    private Usuario findByEmailAndSenha(String email, String senha) {
        return usuarioRepository.findByEmailAndSenha(email, senha).orElseThrow(() -> new NotFoundException("Usuário ou senha inválidos"));
    }
}
