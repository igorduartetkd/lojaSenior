package com.senior.loja.domain.repository;

import com.senior.loja.domain.entity.ItemDoPedido;
import com.senior.loja.domain.entity.Pedido;

import java.util.Set;
import java.util.UUID;

public interface ItemDoPedidoRepository extends BaseDomainRepository<ItemDoPedido> {
    Boolean existsByItemId(UUID itemId);

    Set<ItemDoPedido> findAllByPedido(Pedido pedido);

    Set<ItemDoPedido> saveAll(Set<ItemDoPedido> itens);
}
