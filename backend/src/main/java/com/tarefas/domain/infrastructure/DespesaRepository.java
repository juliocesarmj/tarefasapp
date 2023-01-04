package com.tarefas.domain.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarefas.domain.model.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Integer> {

}
