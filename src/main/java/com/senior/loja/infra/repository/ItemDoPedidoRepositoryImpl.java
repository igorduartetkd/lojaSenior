package com.senior.loja.infra.repository;

import com.senior.loja.domain.entity.ItemDoPedido;
import com.senior.loja.domain.entity.Pedido;
import com.senior.loja.domain.repository.ItemDoPedidoRepository;
import com.senior.loja.infra.database.entity.ItemDoPedidoEntity;
import com.senior.loja.infra.database.repository.ItemDoPedidoEntityRepository;
import com.senior.loja.infra.mapper.ItemDoPedidoMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Component
public class ItemDoPedidoRepositoryImpl implements ItemDoPedidoRepository {

    ItemDoPedidoEntityRepository itemDoPedidoEntityRepository;
    ItemDoPedidoMapper itemDoPedidoMapper;

    public ItemDoPedidoRepositoryImpl(ItemDoPedidoEntityRepository itemDoPedidoEntityRepository,
                                      ItemDoPedidoMapper itemDoPedidoMapper) {
        this.itemDoPedidoEntityRepository = itemDoPedidoEntityRepository;
        this.itemDoPedidoMapper = itemDoPedidoMapper;
    }

    @Override
    public Optional<ItemDoPedido> save(ItemDoPedido domain) {
        ItemDoPedidoEntity itemDoPedidoEntity = itemDoPedidoMapper.toEntity(domain);
        itemDoPedidoEntity = itemDoPedidoEntityRepository.save(itemDoPedidoEntity);
        return itemDoPedidoMapper.toDomain(itemDoPedidoEntity);
    }

    @Override
    public Optional<ItemDoPedido> findById(UUID id) {
        Optional<ItemDoPedidoEntity> itemDoPedidoEntity = itemDoPedidoEntityRepository.findById(id);
        return itemDoPedidoMapper.toDomain(itemDoPedidoEntity);
    }

    @Override
    public Set<ItemDoPedido> findAll() {
        List<ItemDoPedidoEntity> itensDoPedidoEntity = itemDoPedidoEntityRepository.findAll();
        return itemDoPedidoMapper.toDomain(itensDoPedidoEntity);
    }

    @Override
    public void delete(ItemDoPedido domain) {
        itemDoPedidoEntityRepository.findById(domain.getId())
                .ifPresent(itemDoPedidoEntityRepository::delete);
    }

    @Override
    public Boolean existsByItemId(UUID itemId) {
        return itemDoPedidoEntityRepository.existsByItemEntity_Id(itemId);
    }

    @Override
    public Set<ItemDoPedido> findAllByPedido(Pedido pedido) {
        List<ItemDoPedidoEntity> itensDoPedido = itemDoPedidoEntityRepository.findAllByPedidoId(pedido.getId());
        return itemDoPedidoMapper.toDomain(itensDoPedido);
    }

    public Set<ItemDoPedido> saveAll(Set<ItemDoPedido> itens) {
        List<ItemDoPedidoEntity> itensSalvos = itemDoPedidoEntityRepository.saveAll(itemDoPedidoMapper.toEntity(itens));
        return itemDoPedidoMapper.toDomain(itensSalvos);
    }

}
