package com.senior.loja.infra.database.repository;

import com.senior.loja.infra.database.entity.ItemDoPedidoEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemDoPedidoEntityRepository extends BaseEntityRepository<ItemDoPedidoEntity> {
    Boolean existsByItemEntity_Id(UUID itemId);

    List<ItemDoPedidoEntity> findAllByPedidoId(UUID idPedido);
}
