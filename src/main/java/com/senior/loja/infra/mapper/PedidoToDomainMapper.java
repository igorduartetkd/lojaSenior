package com.senior.loja.infra.mapper;

import com.senior.loja.domain.entity.Pedido;
import com.senior.loja.infra.database.entity.PedidoEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PedidoToDomainMapper implements EntityToDomainMapper<PedidoEntity, Pedido> {

    ItemDoPedidoToDomainMapper itemDoPedidoToDomainMapper;

    public PedidoToDomainMapper(ItemDoPedidoToDomainMapper itemDoPedidoToDomainMapper) {
        this.itemDoPedidoToDomainMapper = itemDoPedidoToDomainMapper;
    }

    @Override
    public Optional<Pedido> toDomain(PedidoEntity entity) {
        return entity == null ? Optional.empty() : Optional.of(Pedido.builder()
                .id(entity.getId())
                .itens(itemDoPedidoToDomainMapper.toDomain(entity.getItens()))
                .desconto(entity.getDesconto())
                .situacao(entity.getSituacao())
                .build());
    }
}
