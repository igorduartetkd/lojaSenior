package com.senior.loja.domain.entity;

import com.senior.loja.business.exception.PedidoFechadoException;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@SuperBuilder
@Getter
public class Pedido extends BaseDomain {
    private final UUID id;
    private final Set<ItemDoPedido> itens;
    private BigDecimal desconto = BigDecimal.ZERO;
    private Situacao situacao;

    public void aplicarDesconto(BigDecimal desconto) {
        if (situacao.equals(Situacao.FECHADO)) {
            throw new PedidoFechadoException("aplicar desconto");
        }
        this.desconto = desconto;
    }

    public BigDecimal obterValorComDesconto() {
        return itens.stream()
                .map(itemDoPedido -> itemDoPedido.valorTotalComDesconto(desconto))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void adicionarItem(Item item) {
        if (item.disponivelParaCompra()) {
            adicionarItemNaLista(item);
        }
    }

    public void removerItem(Item item) {
        removerItemDaLista(item);
    }

    private void adicionarItemNaLista(Item item) {
        ItemDoPedido itemDoPedido = recuperarItem(item)
                .orElseGet(() -> ItemDoPedido.builder()
                        .item(item)
                        .qtd(0)
                        .build()
                );
        itemDoPedido.adicionarUmaUnidade();
        itens.add(itemDoPedido);
    }

    private Optional<ItemDoPedido> recuperarItem(Item item) {
        return itens.stream()
                .filter(itemDoPedido -> itemDoPedido.equals(item))
                .findFirst();
    }

    private void removerItemDaLista(Item item) {
        Optional<ItemDoPedido> itemDoPedidoOptional = recuperarItem(item);
        itemDoPedidoOptional.ifPresent(itemDoPedido -> {
            if (itemDoPedido.possuiMaisDeUmaUnidade()) {
                itemDoPedido.removerUmaUnidade();
            } else {
                itens.remove(itemDoPedido);
            }
        });
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
