package com.senior.loja.domain.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void disponivelParaCompra_disponivelQuandoAtivo() {
        // given
        Item item = Item.builder().ativo(Boolean.TRUE).build();

        // when
        Boolean disponivelParaCompra = item.disponivelParaCompra();

        // then
        assertTrue(disponivelParaCompra);
    }

    @Test
    void disponivelParaCompra_naoDisponivelQuandoNaoAtivo() {
        // given
        Item item = Item.builder().ativo(Boolean.FALSE).build();

        // when
        Boolean disponivelParaCompra = item.disponivelParaCompra();

        // then
        assertFalse(disponivelParaCompra);
    }

    @Test
    void obterValorComDesconto_aceitaDesconto() {
        // given
        Item item = Item.builder()
                .preco(BigDecimal.valueOf(100))
                .tipo(Item.Tipo.PRODUTO)
                .build();

        // when
        BigDecimal valorObtido = item.obterValorComDesconto(BigDecimal.valueOf(10));

        // then
        assertEquals(BigDecimal.valueOf(90), valorObtido);

    }

    @Test
    void obterValorComDesconto_naoAceitaDesconto() {
        // given:
        Item servico = Item.builder()
                .preco(BigDecimal.valueOf(100))
                .tipo(Item.Tipo.SERVICO)
                .build();

        // when:
        BigDecimal valorObtido = servico.obterValorComDesconto(BigDecimal.valueOf(10));

        // then:
        assertEquals(BigDecimal.valueOf(100), valorObtido);
    }
}