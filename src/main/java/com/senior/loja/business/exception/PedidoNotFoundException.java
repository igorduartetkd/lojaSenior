package com.senior.loja.business.exception;

import java.util.UUID;

public class PedidoNotFoundException extends RuntimeException {
    public PedidoNotFoundException(UUID id) {
        super(String.format("Pedido não encontrado com id %s", id.toString()));
    }
}
