package com.senior.loja.domain.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    @Test
    void aceitaDesconto() {
        Produto produto = Produto.builder().build();
        assertEquals(Boolean.TRUE, produto.aceitaDesconto());
    }
}