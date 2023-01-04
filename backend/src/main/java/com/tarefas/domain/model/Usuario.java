package com.tarefas.domain.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario extends EntidadeBaseId {
	
	@Column(nullable = false)
	private String nome;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	@JsonIgnore
	@Column(nullable = false)
	private String senha;
	
	@Column(name = "data_cadastro", updatable = false, nullable = false)
	private LocalDate dataCadastro;
	
	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<Despesa> despesas;
	
	@PrePersist
	public void aoPersistir() {
		this.dataCadastro = LocalDate.now();
	}
}
