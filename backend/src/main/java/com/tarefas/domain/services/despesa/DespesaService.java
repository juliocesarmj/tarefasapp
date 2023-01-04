package com.tarefas.domain.services.despesa;

import com.tarefas.domain.model.Despesa;

import java.util.Set;

public interface DespesaService {

    Despesa novaDespesa(Despesa despesa);

    Despesa atualizar(final int id, Despesa despesa);

    Set<Despesa> listarDespesas(final int usuarioId);

    Set<Despesa> todasDespesas();

    Despesa findById(final int id);

    Despesa deletar(final int id);
}
