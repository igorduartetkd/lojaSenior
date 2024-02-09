package com.senior.loja.infra.mapper;

import com.senior.loja.domain.entity.Item;
import com.senior.loja.infra.database.entity.ItemEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ItemMapper implements EntityToDomainMapper<ItemEntity, Item>,
        DomainToEntityMapper<Item, ItemEntity> {

    @Override
    public Optional<Item> toDomain(ItemEntity entity) {
        return entity == null ? Optional.empty() : Optional.of(Item.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .preco(entity.getPreco())
                .ativo(entity.getAtivo())
                .tipo(entity.getTipo())
                .build());
    }

    @Override
    public ItemEntity toEntity(Item domain) {
        return domain == null ? null : ItemEntity.builder()
                .id(domain.getId())
                .nome(domain.getNome())
                .preco(domain.getPreco())
                .ativo(domain.getAtivo())
                .tipo(domain.getTipo())
                .build();
    }
}
