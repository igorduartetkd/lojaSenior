package com.senior.loja.business.exception;

public class PedidoFechadoException extends RuntimeException {
    public PedidoFechadoException(String operacao) {
        super(String.format("Não é possível %s em um pedido fechado", operacao));
    }
}
