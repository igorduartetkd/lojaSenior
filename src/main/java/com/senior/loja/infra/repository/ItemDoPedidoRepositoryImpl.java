package com.senior.loja.infra.repository;

import com.senior.loja.domain.repository.ItemDoPedidoRepository;
import com.senior.loja.infra.database.repository.ItemDoPedidoEntityRepository;
import org.springframework.stereotype.Component;

@Component
public class ItemDoPedidoRepositoryImpl implements ItemDoPedidoRepository {

    ItemDoPedidoEntityRepository itemDoPedidoEntityRepository;

    public ItemDoPedidoRepositoryImpl(ItemDoPedidoEntityRepository itemDoPedidoEntityRepository) {
        this.itemDoPedidoEntityRepository = itemDoPedidoEntityRepository;
    }
}
