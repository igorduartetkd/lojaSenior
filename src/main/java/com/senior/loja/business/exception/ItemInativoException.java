package com.senior.loja.business.exception;

import java.util.UUID;

public class ItemInativoException extends RuntimeException {
    public ItemInativoException(UUID id) {
        super(String.format("O item de id %s está desativado", id.toString()));
    }
}
