package com.tarefas.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.tarefas.application.dtos.despesa.DespesaDTO;
import com.tarefas.domain.enums.StatusDespesa;
import com.tarefas.domain.enums.TipoDespesa;

import lombok.*;
import org.springframework.security.core.context.SecurityContext;

@Getter
@Setter(AccessLevel.PROTECTED)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "despesa")
public class Despesa extends EntidadeBaseId {
	
	@Column(nullable = false)
	private String titulo;
	private String descricao;
	
	@Column(nullable = false)
	private BigDecimal valor;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoDespesa tipoDespesa;
	
	@Column(nullable = false)
	private LocalDate dataDespesa;
	
	@Column(nullable = false, updatable = false)
	private LocalDateTime dataCadastro;

	private boolean recorrente;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusDespesa statusDespesa;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@PrePersist
	public void aoPersistir() {
		this.dataCadastro = LocalDateTime.now();
	}

	public static Despesa novo(DespesaDTO dto) {
		Despesa despesa = new Despesa();
		despesa.setTitulo(dto.getTitulo());
		despesa.setDescricao(dto.getDescricao());
		despesa.setTipoDespesa(dto.getTipoDespesa());
		despesa.setDataDespesa(dto.getDataDespesa());
		despesa.setRecorrente(dto.isRecorrente());
		despesa.setValor(dto.getValor());
		despesa.setStatusDespesa(dto.getStatusDespesa());
		return despesa;
	}

	public void atribuirDespesaUsuario(Usuario usuario){
		setUsuario(usuario);
	}
}
