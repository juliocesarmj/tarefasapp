package com.tarefas.infrastructure.repositories;

import com.tarefas.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tarefas.domain.model.Despesa;

import java.util.List;

public interface DespesaRepository extends JpaRepository<Despesa, Integer> {

    List<Despesa> findByUsuario(Usuario usuario);
}
