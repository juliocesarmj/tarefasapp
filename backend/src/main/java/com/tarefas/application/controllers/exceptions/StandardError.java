package com.tarefas.application.controllers.exceptions;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
public class StandardError {

    private LocalDateTime timestamp;
    private int status;
    private String erro;
    private String path;
    private Map<String, String> erros;
}
