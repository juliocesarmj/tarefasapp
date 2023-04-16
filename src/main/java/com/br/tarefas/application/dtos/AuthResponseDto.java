package com.br.tarefas.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDto {

    private String mensagem;
    private String nome;
    private String email;
    private String accessToken;
}
