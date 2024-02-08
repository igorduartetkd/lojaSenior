package com.senior.loja.domain.entity;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@SuperBuilder
public abstract class Item extends BaseDomain {
    private final UUID id;
    private final String nome;
    private final BigDecimal preco;
    private Boolean ativo;

    abstract protected Boolean aceitaDesconto();

    public Boolean disponivelParaCompra() {
        return ativo;
    }

    public void ativar() {
        ativo = Boolean.TRUE;
    }

    public void desativar() {
        ativo = Boolean.FALSE;
    }

    public BigDecimal obterValorComDesconto(BigDecimal percentualDesconto) {
        if (aceitaDesconto()) {
            return valorComDesconto(percentualDesconto);
        }
        return preco;
    }

    private BigDecimal valorComDesconto(BigDecimal porcentagemDesconto) {
        return preco.subtract(valorDoDesconto(porcentagemDesconto));
    }

    private BigDecimal valorDoDesconto(BigDecimal porcentagemDesconto) {
        return preco.multiply(porcentagemDesconto).divide(BigDecimal.valueOf(100));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Item item)) {
            return false;
        }

        return this.id.equals(item.id);
    }
}
