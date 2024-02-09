package com.senior.loja.business.exception;

import java.util.UUID;

public class ItemNaoEstaNoPedidoException extends RuntimeException {
    public ItemNaoEstaNoPedidoException(UUID id) {
        super(String.format("O item de id %s não está no pedido", id.toString()));
    }
}
