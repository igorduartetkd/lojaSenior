package com.senior.loja.domain.repository;

import com.senior.loja.domain.entity.ItemDoPedido;

import java.util.UUID;

public interface ItemDoPedidoRepository extends BaseDomainRepository<ItemDoPedido> {
    Boolean existsByItemId(UUID itemId);
}
