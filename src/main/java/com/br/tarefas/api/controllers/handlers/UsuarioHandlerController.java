package com.br.tarefas.api.controllers.handlers;

import com.br.tarefas.domain.service.exceptions.EntityExistsException;
import com.br.tarefas.domain.service.exceptions.NotFoundException;
import com.br.tarefas.infrastructure.security.exceptions.SecurityAppException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UsuarioHandlerController {

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<ErroHandler> handlerEntityExistsException(EntityExistsException e, HttpServletRequest req) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(badRequest).body(ErroHandler.builder()
                .timestamp(System.currentTimeMillis())
                .status(badRequest.value())
                .erro(e.getMessage())
                .path(req.getRequestURI())
                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroHandler> handlerMethodArgumentNotValidException(MethodArgumentNotValidException m, HttpServletRequest req) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(badRequest).body(ErroHandler.builder()
                .timestamp(System.currentTimeMillis())
                .status(badRequest.value())
                .erro(m.getBindingResult().getAllErrors().get(0).getDefaultMessage())
                .path(req.getRequestURI())
                .build());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErroHandler> handlerNotFoundException(NotFoundException e, HttpServletRequest req) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(badRequest).body(ErroHandler.builder()
                .timestamp(System.currentTimeMillis())
                .status(badRequest.value())
                .erro(e.getMessage())
                .path(req.getRequestURI())
                .build());
    }

    @ExceptionHandler(SecurityAppException.class)
    public ResponseEntity<ErroHandler> handlerSecurityAppException(SecurityAppException e, HttpServletRequest req) {
        HttpStatus unauthorized = HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(unauthorized).body(ErroHandler.builder()
                .timestamp(System.currentTimeMillis())
                .status(unauthorized.value())
                .erro(e.getMessage())
                .path(req.getRequestURI())
                .build());
    }

}
