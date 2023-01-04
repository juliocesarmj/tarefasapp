package com.tarefas.domain.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarefas.domain.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
