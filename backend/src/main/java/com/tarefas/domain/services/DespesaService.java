package com.tarefas.domain.services;

import java.util.Set;

import com.tarefas.domain.model.Despesa;

public interface DespesaService {

	Despesa novaDespesa(Despesa despesa);

	Despesa atualizar(final int id, Despesa despesa);

	Set<Despesa> listarDespesas(final int usuarioId);

	Set<Despesa> todasDespesas();

	Despesa findById(final int id);
	
	Despesa deletar(final int id);
}
