package com.br.tarefas.api.controllers.handlers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErroHandler {

    private long timestamp;
    private int status;
    private String erro;
    private String path;
}
