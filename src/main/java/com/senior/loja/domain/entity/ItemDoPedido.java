package com.senior.loja.domain.entity;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@SuperBuilder
public class ItemDoPedido extends BaseDomain {
    private final Item item;
    private Integer qtd;

    public BigDecimal valorTotalComDesconto(BigDecimal percentualDesconto) {
        BigDecimal valorUnitarioComDesconto = item.obterValorComDesconto(percentualDesconto);
        return BigDecimal.valueOf(qtd).multiply(valorUnitarioComDesconto);
    }

    public Boolean possuiMaisDeUmaUnidade() {
        if (qtd > 1) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public void adicionarUmaUnidade() {
        qtd++;
    }

    public void removerUmaUnidade() {
        qtd--;
    }

    public boolean equals(Item item) {
        return this.item.equals(item);
    }
}
