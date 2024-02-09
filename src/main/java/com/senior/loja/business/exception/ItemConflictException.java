package com.senior.loja.business.exception;

import java.util.UUID;

public class ItemConflictException extends RuntimeException {
    public ItemConflictException(UUID id) {
        super(String.format("O item de id %s está sendo referenciado em um pedido", id.toString()));
    }
}
