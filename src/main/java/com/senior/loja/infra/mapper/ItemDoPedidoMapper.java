package com.senior.loja.infra.mapper;

import com.senior.loja.domain.entity.ItemDoPedido;
import com.senior.loja.infra.database.entity.ItemDoPedidoEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ItemDoPedidoMapper implements EntityToDomainMapper<ItemDoPedidoEntity, ItemDoPedido>,
        DomainToEntityMapper<ItemDoPedido, ItemDoPedidoEntity> {

    ItemMapper itemMapper;
    PedidoMapper pedidoMapper;

    public ItemDoPedidoMapper(ItemMapper itemMapper, PedidoMapper pedidoMapper) {
        this.itemMapper = itemMapper;
        this.pedidoMapper = pedidoMapper;
    }


    @Override
    public Optional<ItemDoPedido> toDomainOptional(ItemDoPedidoEntity entity) {
        return entity == null ? Optional.empty() : Optional.of(ItemDoPedido.builder()
                .id(entity.getId())
                .item(itemMapper.toDomainOptional(entity.getItemEntity()).orElse(null))
                .qtd(entity.getQtd())
                .pedido(pedidoMapper.toDomainOptional(entity.getPedido()).orElse(null))
                .build());
    }

    @Override
    public ItemDoPedidoEntity toEntity(ItemDoPedido domain) {
        return domain == null ? null : ItemDoPedidoEntity.builder()
                .id(domain.getId())
                .itemEntity(itemMapper.toEntity(domain.getItem()))
                .qtd(domain.getQtd())
                .pedido(pedidoMapper.toEntity(domain.getPedido()))
                .build();
    }
}
