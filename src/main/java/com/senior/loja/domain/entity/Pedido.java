package com.senior.loja.domain.entity;

import com.senior.loja.business.exception.ItemInativoException;
import com.senior.loja.business.exception.ItemNaoEstaNoPedidoException;
import com.senior.loja.business.exception.PedidoFechadoException;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

@SuperBuilder
@Getter
@Setter
public class Pedido extends BaseDomain {
    private Set<ItemDoPedido> itens;
    private BigDecimal desconto;
    private Situacao situacao;

    public void abrirPedido() {
        situacao = Situacao.ABERTO;
    }

    public void fecharPedido() {
        situacao = Situacao.FECHADO;
    }

    public Boolean pedidoAberto() {
        if (situacao == Situacao.ABERTO) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Boolean pedidoFechado() {
        if (situacao == Situacao.FECHADO) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public void aplicarDesconto(BigDecimal desconto) {
        if (pedidoFechado()) {
            throw new PedidoFechadoException("aplicar desconto");
        }
        this.desconto = desconto;
    }

    public BigDecimal obterValorComDesconto() {
        return itens == null ? BigDecimal.ZERO : itens.stream()
                .map(itemDoPedido -> itemDoPedido.valorTotalComDesconto(desconto))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public ItemDoPedido adicionarItem(Item item) {
        if (pedidoFechado()) {
            throw new PedidoFechadoException("adicionar item");
        }
        if (item.disponivelParaCompra()) {
            ItemDoPedido itemDoPedido = recuperarItem(item)
                    .orElseGet(() -> ItemDoPedido.builder()
                            .item(item)
                            .qtd(0)
                            .pedido(this)
                            .build()
                    );
            itemDoPedido.adicionarUmaUnidade();
            itens.add(itemDoPedido);
            return itemDoPedido;
        } else {
            throw new ItemInativoException(item.getId());
        }
    }

    public ItemDoPedido removerItem(Item item) {
        if (pedidoFechado()) {
            throw new PedidoFechadoException("remover item");
        }
        Optional<ItemDoPedido> itemDoPedidoOptional = recuperarItem(item);
        return itemDoPedidoOptional.map(itemDoPedido -> {
            itemDoPedido.removerUmaUnidade();
            if (itemDoPedido.qtdChegouAZero()) {
                itens.remove(itemDoPedido);
            }
            return itemDoPedido;
        }).orElseThrow(() -> new ItemNaoEstaNoPedidoException(item.getId()));
    }

    private Optional<ItemDoPedido> recuperarItem(Item item) {
        return itens.stream()
                .filter(itemDoPedido -> itemDoPedido.equals(item))
                .findFirst();
    }

    @Getter
    public enum Situacao {
        ABERTO("Aberto"),
        FECHADO("Fechado");

        private final String name;

        Situacao(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
