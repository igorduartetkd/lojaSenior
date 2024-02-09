package com.senior.loja.business.dto;

import com.senior.loja.domain.entity.Item;
import com.senior.loja.domain.entity.Pedido;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class ItemDoPedidoDTO {
    UUID id;
    Integer qtd;
    ItemDTO item;
    PedidoDTO pedido;
}
