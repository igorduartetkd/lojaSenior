package com.senior.loja.domain.entity;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class Servico extends Item {

    @Override
    protected Boolean aceitaDesconto() {
        return Boolean.FALSE;
    }
}
