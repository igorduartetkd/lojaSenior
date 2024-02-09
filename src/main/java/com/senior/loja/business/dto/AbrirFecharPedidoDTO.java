package com.senior.loja.business.dto;

import com.senior.loja.domain.entity.Pedido;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class AbrirFecharPedidoDTO {
    @NotNull
    UUID id;

    Pedido.Situacao situacao;
}
