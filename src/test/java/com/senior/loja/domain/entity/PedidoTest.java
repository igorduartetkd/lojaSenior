package com.senior.loja.domain.entity;

import com.senior.loja.business.exception.PedidoFechadoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PedidoTest {
    private Pedido pedido;
    private Item itemMock;

    @BeforeEach
    void setUp() {
        Set<ItemDoPedido> itens = new HashSet<>();
        itemMock = mock(Item.class);
        pedido = Pedido.builder()
                .id(UUID.randomUUID())
                .itens(itens)
                .situacao(Pedido.Situacao.ABERTO)
                .desconto(BigDecimal.ZERO)
                .build();
    }

    @Test
    void aplicarDesconto_pedidoAberto() {
        // given
        BigDecimal desconto = BigDecimal.TEN;

        // when
        pedido.aplicarDesconto(desconto);

        // then
        assertEquals(desconto, pedido.getDesconto());
    }

    @Test
    void aplicarDesconto_pedidoFechado() {
        // given
        pedido = Pedido.builder().situacao(Pedido.Situacao.FECHADO).build();

        // when e then
        PedidoFechadoException exception = assertThrows(PedidoFechadoException.class, () -> pedido.aplicarDesconto(BigDecimal.TEN));
        assertEquals("Não é possível aplicar desconto em um pedido fechado", exception.getMessage());
        assertNull(pedido.getDesconto());
    }

    @Test
    void obterValorComDesconto() {
        // given
        ItemDoPedido itemDoPedidoMock = mock(ItemDoPedido.class);
        when(itemDoPedidoMock.valorTotalComDesconto(BigDecimal.ZERO)).thenReturn(BigDecimal.valueOf(100));
        pedido.getItens().add(itemDoPedidoMock);
        ItemDoPedido itemDoPedidoMock2 = mock(ItemDoPedido.class);
        when(itemDoPedidoMock2.valorTotalComDesconto(BigDecimal.ZERO)).thenReturn(BigDecimal.valueOf(50));
        pedido.getItens().add(itemDoPedidoMock2);

        // when
        BigDecimal valorTotalComDesconto = pedido.obterValorComDesconto();

        // then
        assertEquals(BigDecimal.valueOf(150), valorTotalComDesconto);

    }

    @Test
    void adicionarItem_itemDisponivel() {
        // given
        when(itemMock.disponivelParaCompra()).thenReturn(true);

        // when
        pedido.adicionarItem(itemMock);

        // then
        assertEquals(1, pedido.getItens().size());

    }

    @Test
    void adicionarItem_itemNaoDisponivel() {
        // given
        when(itemMock.disponivelParaCompra()).thenReturn(false);

        // when
        pedido.adicionarItem(itemMock);

        // then
        assertTrue(pedido.getItens().isEmpty());

    }

    @Test
    void removerItem() {
        // given
        when(itemMock.disponivelParaCompra()).thenReturn(true);
        pedido.adicionarItem(itemMock);

        // when
        pedido.removerItem(itemMock);

        // then
        assertTrue(pedido.getItens().isEmpty());
    }
}