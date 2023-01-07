package com.tarefas.application.controllers;

import com.tarefas.application.dtos.despesa.DespesaDTO;
import com.tarefas.domain.enums.TipoDespesa;
import com.tarefas.domain.services.despesa.DespesaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(path = "/v1/despesa")
@RequiredArgsConstructor
public class DespesaController {

    private final DespesaService service;

    @PostMapping
    public ResponseEntity<DespesaDTO> nova(@RequestBody @Valid DespesaDTO dto, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.novaDespesa(dto, request));
    }

    @GetMapping
    public ResponseEntity<Set<DespesaDTO>> consultarDespesas(HttpServletRequest request) {
        return ResponseEntity.ok(service.todasDespesas(request));
    }
    
    @GetMapping(path = "/tiposdespesas")
    public ResponseEntity<TipoDespesa[]> tiposDespesa() {
        return ResponseEntity.ok(TipoDespesa.values());
    }
}
