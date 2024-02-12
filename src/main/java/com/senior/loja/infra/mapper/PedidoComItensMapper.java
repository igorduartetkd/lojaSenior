package com.senior.loja.infra.mapper;

import com.senior.loja.domain.entity.Pedido;
import com.senior.loja.infra.database.entity.PedidoEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PedidoComItensMapper implements EntityToDomainMapper<PedidoEntity, Pedido>,
        DomainToEntityMapper<Pedido, PedidoEntity> {

    ItemDoPedidoMapper itemDoPedidoMapper;

    public PedidoComItensMapper(ItemDoPedidoMapper itemDoPedidoMapper) {
        this.itemDoPedidoMapper = itemDoPedidoMapper;
    }

    @Override
    public Optional<Pedido> toDomainOptional(PedidoEntity entity) {
        return entity == null ? Optional.empty() : Optional.of(Pedido.builder()
                .id(entity.getId())
                .desconto(entity.getDesconto())
                .situacao(entity.getSituacao())
                .itens(itemDoPedidoMapper.toDomainOptional(entity.getItens()))
                .build());
    }

    @Override
    public PedidoEntity toEntity(Pedido domain) {
        return domain == null ? null : PedidoEntity.builder()
                .id(domain.getId())
                .desconto(domain.getDesconto())
                .situacao(domain.getSituacao())
                .itens(itemDoPedidoMapper.toEntity(domain.getItens()))
                .build();
    }
}
