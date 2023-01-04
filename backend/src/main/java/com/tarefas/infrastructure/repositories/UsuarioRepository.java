package com.tarefas.infrastructure.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarefas.domain.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	Optional<Usuario> findByEmailAndSenha(String email, String senha);
}
