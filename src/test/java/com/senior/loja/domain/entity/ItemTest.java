package com.senior.loja.domain.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void disponivelParaCompra_disponivelQuandoAtivo() {
        // given
        Produto produto = Produto.builder().ativo(Boolean.TRUE).build();

        // when
        Boolean disponivelParaCompra = produto.disponivelParaCompra();

        // then
        assertTrue(disponivelParaCompra);
    }

    @Test
    void disponivelParaCompra_naoDisponivelQuandoNaoAtivo() {
        // given
        Produto produto = Produto.builder().ativo(Boolean.FALSE).build();

        // when
        Boolean disponivelParaCompra = produto.disponivelParaCompra();

        // then
        assertFalse(disponivelParaCompra);
    }

    @Test
    void obterValorComDesconto_aceitaDesconto() {
        // given
        Produto produto = Produto.builder().preco(BigDecimal.valueOf(100)).build();

        // when
        BigDecimal valorObtido = produto.obterValorComDesconto(BigDecimal.valueOf(10));

        // then
        assertEquals(BigDecimal.valueOf(90), valorObtido);

    }

    @Test
    void obterValorComDesconto_naoAceitaDesconto() {
        // given:
        Servico servico = Servico.builder().preco(BigDecimal.valueOf(100)).build();

        // when:
        BigDecimal valorObtido = servico.obterValorComDesconto(BigDecimal.valueOf(10));

        // then:
        assertEquals(BigDecimal.valueOf(100), valorObtido);
    }
}