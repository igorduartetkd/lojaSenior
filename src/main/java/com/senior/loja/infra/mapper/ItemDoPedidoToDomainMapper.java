package com.senior.loja.infra.mapper;

import com.senior.loja.domain.entity.ItemDoPedido;
import com.senior.loja.infra.database.entity.ItemDoPedidoEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ItemDoPedidoToDomainMapper implements EntityToDomainMapper<ItemDoPedidoEntity, ItemDoPedido> {

    ItemToDomainMapper itemToDomainMapper;

    public ItemDoPedidoToDomainMapper(ItemToDomainMapper itemToDomainMapper) {
        this.itemToDomainMapper = itemToDomainMapper;
    }

    @Override
    public Optional<ItemDoPedido> toDomain(ItemDoPedidoEntity entity) {
        return entity == null ? Optional.empty() : Optional.of(ItemDoPedido.builder()
                .id(entity.getId())
                .item(itemToDomainMapper.toDomain(entity.getItemEntity()).orElse(null))
                .qtd(entity.getQtd())
                .build());
    }
}
