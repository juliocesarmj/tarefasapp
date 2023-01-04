package com.tarefas.application.dtos.despesa;

import com.tarefas.domain.enums.TipoDespesa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DespesaDTO {

    @NotBlank(message = "Campo título é obrigatório.")
    private String titulo;
    private String descricao;

    @Min(value = 1, message = "Informe um valor de despesa válido.")
    private BigDecimal valor;

    @NotNull(message = "Campo tipoDespesa é obrigatório.")
    private TipoDespesa tipoDespesa;

    @NotNull(message = "Campo dataDespesa é obrigatório.")
    private LocalDate dataDespesa;

    @NotNull(message = "Campo recorrente é obrigatório.")
    private boolean recorrente;
}
