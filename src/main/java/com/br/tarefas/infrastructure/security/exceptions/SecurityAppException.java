package com.br.tarefas.infrastructure.security.exceptions;

public class SecurityAppException extends RuntimeException {

    public SecurityAppException(String mensagem) {
        super(mensagem);
    }
}
