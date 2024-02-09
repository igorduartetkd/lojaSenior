package com.senior.loja.infra.repository;

import com.senior.loja.domain.entity.Item;
import com.senior.loja.domain.repository.ItemRepository;
import com.senior.loja.infra.database.entity.ItemEntity;
import com.senior.loja.infra.database.repository.ItemEntityRepository;
import com.senior.loja.infra.mapper.ItemMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Component
public class ItemRepositoryImpl implements ItemRepository {

    ItemEntityRepository itemEntityRepository;
    ItemMapper itemMapper;

    public ItemRepositoryImpl(ItemEntityRepository itemEntityRepository, ItemMapper itemMapper) {
        this.itemEntityRepository = itemEntityRepository;
        this.itemMapper = itemMapper;
    }

    @Override
    public Optional<Item> save(Item item) {
        ItemEntity itemEntity = itemMapper.toEntity(item);
        itemEntity = itemEntityRepository.save(itemEntity);
        return itemMapper.toDomainOptional(itemEntity);
    }

    @Override
    public Optional<Item> findById(UUID id) {
        Optional<ItemEntity> itemEntity = itemEntityRepository.findById(id);
        return itemMapper.toDomainOptional(itemEntity);
    }

    @Override
    public Set<Item> findAll() {
        List<ItemEntity> itensEntity = itemEntityRepository.findAll();
        return itemMapper.toDomainOptional(itensEntity);
    }

    public Page<Item> findAll(Pageable pageable) {
        Page<ItemEntity> itensEntity = itemEntityRepository.findAll(pageable);
        return itensEntity.map(itemMapper::toDomain);
    }

    @Override
    public void delete(Item item) {
        itemEntityRepository.findById(item.getId())
                .ifPresent(itemEntityRepository::delete);
    }
}
