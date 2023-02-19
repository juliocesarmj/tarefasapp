package com.tarefas.domain.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario extends EntidadeBaseId {
	
	@Column(nullable = false)
	private String nome;
	
	@Column(unique = true, nullable = false, updatable = false)
	private String email;
	
	@JsonIgnore
	@Column(nullable = false)
	private String senha;
	
	@Column(name = "data_cadastro", updatable = false, nullable = false)
	private LocalDate dataCadastro;
	
	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<Despesa> despesas;
	
	public Usuario() {
	}
	
	public Usuario(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
	
	@PrePersist
	public void aoPersistir() {
		this.dataCadastro = LocalDate.now();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(email);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(email, other.email);
	}
}
