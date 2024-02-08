package com.senior.loja.domain.entity;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class Produto extends Item {

    @Override
    protected Boolean aceitaDesconto() {
        return Boolean.TRUE;
    }
}
