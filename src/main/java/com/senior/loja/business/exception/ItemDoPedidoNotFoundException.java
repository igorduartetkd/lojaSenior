package com.senior.loja.business.exception;

import java.util.UUID;

public class ItemDoPedidoNotFoundException extends RuntimeException {
    public ItemDoPedidoNotFoundException(UUID id) {
        super(String.format("Item do pedido não encontrado com id %s", id.toString()));
    }
}
