package com.tarefas.application.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> handlerMethodArgumentNotValidException(MethodArgumentNotValidException m, HttpServletRequest req) {

        Map<String, String> erros = new HashMap<>();

        final HttpStatus status = HttpStatus.BAD_REQUEST;

        m.getBindingResult().getFieldErrors().forEach(fieldError ->
            erros.put(fieldError.getField(), fieldError.getDefaultMessage())
        );

        StandardError erro = StandardError.builder()
                .erro("Erro ao processar uma nova despesa")
                .status(status.value())
                .timestamp(LocalDateTime.now())
                .erros(erros)
                .path(req.getRequestURI())
                .build();

        return ResponseEntity.status(status).body(erro);
    }
}
