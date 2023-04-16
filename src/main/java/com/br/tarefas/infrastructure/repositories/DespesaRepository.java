package com.br.tarefas.infrastructure.repositories;

import com.br.tarefas.domain.enums.TipoDespesa;
import com.br.tarefas.domain.model.Despesa;
import com.br.tarefas.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface DespesaRepository extends JpaRepository<Despesa, Integer> {

    Despesa findByIdAndUsuario(Integer id, Usuario usuario);
    List<Despesa> findByTipoDespesaAndUsuario(TipoDespesa tipoDespesa, Usuario usuario);

    @Query("SELECT obj FROM Despesa obj where obj.dataVencimento between :dataInicio and :dataFim "
            + "and obj.usuario = :usuario "
            + "order by obj.dataVencimento desc")
    List<Despesa> obterDespesasPorDataVencimento(LocalDate dataInicio, LocalDate dataFim, Usuario usuario);

    @Query("SELECT obj FROM Despesa obj where obj.dataCadastro between :dataInicio and :dataFim "
            + "and obj.usuario = :usuario "
            + "order by obj.dataCadastro desc")
    List<Despesa> obterDespesasPorDataCadastro(LocalDate dataInicio, LocalDate dataFim, Usuario usuario);
}
