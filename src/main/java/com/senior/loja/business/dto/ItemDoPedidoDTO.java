package com.senior.loja.business.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ItemDoPedidoDTO {
    Integer qtd;
    ItemDTO item;
    PedidoDTO pedido;
}
