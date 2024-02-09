package com.senior.loja.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ItemDoPedidoTest {
    @Mock
    private Item item;

    private ItemDoPedido itemDoPedido;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        itemDoPedido = ItemDoPedido.builder()
                .item(item)
                .qtd(5)
                .build();
    }

    @Test
    void valorTotalComDesconto() {
        // given
        BigDecimal desconto = BigDecimal.valueOf(25);
        BigDecimal valorUnitarioComDescontoMock = BigDecimal.valueOf(2);
        when(item.obterValorComDesconto(desconto)).thenReturn(valorUnitarioComDescontoMock);

        // when
        BigDecimal valorTotalComDesconto = itemDoPedido.valorTotalComDesconto(desconto);

        //then
        BigDecimal valorEsperado = BigDecimal.valueOf(10); // 2 * 5
        assertEquals(valorEsperado, valorTotalComDesconto,
                "O valor total com desconto deve ser o produto entre o valor unitario com desconto e a qtd");
    }

    @Test
    void possuiMaisDeUmaUnidade() {
        // when
        Boolean possuiMaisDeUmaUnidade = itemDoPedido.qtdChegouAZero();

        //then
        assertTrue(possuiMaisDeUmaUnidade);
    }

    @Test
    void possuiMaisDeUmaUnidade_retornaFalse_quandoPossuiApenasUmaUnidade() {
        // given
        ItemDoPedido item = ItemDoPedido.builder().qtd(1).build();

        // when
        Boolean possuiMaisDeUmaUnidade = item.qtdChegouAZero();

        //then
        assertFalse(possuiMaisDeUmaUnidade);
    }

    @Test
    void adicionarUmaUnidade() {
        // when
        itemDoPedido.adicionarUmaUnidade();

        //then
        assertEquals(6, itemDoPedido.getQtd());
    }

    @Test
    void removerUmaUnidade() {
        // when
        itemDoPedido.removerUmaUnidade();

        //then
        assertEquals(4, itemDoPedido.getQtd());
    }
}