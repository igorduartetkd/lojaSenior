package com.senior.loja.domain.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServicoTest {

    @Test
    void aceitaDesconto() {
        Servico servico = Servico.builder().build();
        assertEquals(Boolean.FALSE, servico.aceitaDesconto());
    }
}