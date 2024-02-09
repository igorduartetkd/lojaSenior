package com.senior.loja.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder
public class Item extends BaseDomain {
    private String nome;
    private BigDecimal preco;
    private Boolean ativo;
    private Tipo tipo;

    private Boolean aceitaDesconto() {
        if (tipo.equals(Tipo.PRODUTO)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

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

        return this.getId().equals(item.getId());
    }

    public enum Tipo {
        PRODUTO,
        SERVICO
    }
}
