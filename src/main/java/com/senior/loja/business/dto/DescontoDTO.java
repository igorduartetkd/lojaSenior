package com.senior.loja.business.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
@Builder
public class DescontoDTO {
    @NotNull
    UUID id;

    @NotNull
    @DecimalMin(value = "0", inclusive = false, message = "Deve ser positivo")
    @DecimalMax(value = "100", inclusive = false, message = "Deve ser menor que 100")
    BigDecimal percentualDeDesconto;

    PedidoDTO pedido;
}
