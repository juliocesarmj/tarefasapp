package com.br.tarefas.domain.service.exceptions;

public class EntityExistsException extends RuntimeException {

    public EntityExistsException(String mensagem) {
        super(mensagem);
    }
}
