package com.br.tarefas.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false, updatable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    private boolean ativo;

    @Column(updatable = false, nullable = false)
    private LocalDate dataCadastro;

    @Column(insertable = false)
    private LocalDate dataAtualizacao;
    private LocalDate dataInativacao;

    @OneToMany(mappedBy = "usuario")
    private List<Despesa> despesas;

    @Transient
    private String accessToken;

    @PrePersist
    public void doPersist() {
        dataCadastro = LocalDate.now();
        ativo = true;
    }

    @PreUpdate
    public void doUpdate() {
        dataAtualizacao = LocalDate.now();
    }

    public void inserirSenha(String senha) {
        this.senha = senha;
    }
}
