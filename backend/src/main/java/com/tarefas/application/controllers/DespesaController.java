package com.tarefas.application.controllers;

import com.tarefas.application.dtos.despesa.DespesaDTO;
import com.tarefas.domain.services.despesa.DespesaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/v1/despesa")
@RequiredArgsConstructor
public class DespesaController {

    private final DespesaService service;

    @PostMapping
    public ResponseEntity<DespesaDTO> nova(@RequestBody @Valid DespesaDTO dto, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.novaDespesa(dto, request));
    }
}
