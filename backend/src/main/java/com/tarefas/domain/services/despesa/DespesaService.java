package com.tarefas.domain.services.despesa;

import com.tarefas.application.dtos.despesa.DespesaDTO;
import com.tarefas.domain.model.Despesa;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public interface DespesaService {

    DespesaDTO novaDespesa(DespesaDTO dto, HttpServletRequest request);

    Despesa atualizar(final int id, Despesa despesa);

    Set<DespesaDTO> todasDespesas(HttpServletRequest request);

    Despesa findById(final int id);

    Despesa deletar(final int id);
}
