package com.senior.loja.business.dto;

import com.senior.loja.domain.entity.Item;
import com.senior.loja.domain.entity.ItemDoPedido;
import com.senior.loja.domain.entity.Pedido;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Value
@Builder
public class PedidoDTO {
    UUID id;


    @DecimalMin(value = "0", inclusive = false, message = "Deve ser positivo")
    @DecimalMax(value = "100", inclusive = false, message = "Deve ser menor que 100")
    BigDecimal desconto;

    BigDecimal valorTotal;

    @NotNull(message = "Não deve ser nulo")
    Pedido.Situacao situacao;

    Set<ItemDoPedidoDTO> itens;
}
