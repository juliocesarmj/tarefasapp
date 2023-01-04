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

import com.tarefas.domain.enums.TipoDespesa;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
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
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@PrePersist
	public void aoPersistir() {
		this.dataCadastro = LocalDateTime.now();
	}
}
