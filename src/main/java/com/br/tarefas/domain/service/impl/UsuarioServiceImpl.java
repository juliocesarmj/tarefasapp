package com.br.tarefas.domain.service.impl;

import com.br.tarefas.domain.model.Usuario;
import com.br.tarefas.domain.service.components.MD5Component;
import com.br.tarefas.domain.service.exceptions.EntityExistsException;
import com.br.tarefas.domain.service.interfaces.UsuarioService;
import com.br.tarefas.infrastructure.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    MD5Component md5Component;

    @Override
    public void criar(Usuario usuario) {
        validaEmailExistente(usuario.getEmail());
        usuario.inserirSenha(md5Component.encrypt(usuario.getSenha()));
        usuarioRepository.save(usuario);
    }

    @Override
    public void atualizar(Usuario usuario) {
        Usuario usuarioAtualizado = findByEmail(usuario.getEmail());
        if (usuario.getNome() != null) {
            usuarioAtualizado.setNome(usuario.getNome());
        } else {
            usuario.setNome(usuarioAtualizado.getNome());
        }
        if(usuario.getSenha() != null) {
            usuarioAtualizado.inserirSenha(md5Component.encrypt(usuario.getSenha()));
        }
        usuarioRepository.save(usuarioAtualizado);
    }

    private Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(() ->
                new EntityExistsException("Usuário não encontrado."));

    }

    private void validaEmailExistente(String email) {
        usuarioRepository.findByEmail(email)
                .ifPresent(usuario -> {
                    throw new EntityExistsException("O email " + usuario.getEmail() + " já existe.");
                });
    }
}
