package com.senior.loja.business.exception;

import java.util.UUID;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(UUID id) {
        super(String.format("Item não encontrado com o id %s", id.toString()));
    }
}
