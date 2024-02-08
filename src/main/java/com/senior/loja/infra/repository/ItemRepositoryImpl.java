package com.senior.loja.infra.repository;

import com.senior.loja.domain.repository.ItemRepository;
import com.senior.loja.infra.database.repository.ItemEntityRepository;
import org.springframework.stereotype.Component;

@Component
public class ItemRepositoryImpl implements ItemRepository {

    ItemEntityRepository itemEntityRepository;

    public ItemRepositoryImpl(ItemEntityRepository itemEntityRepository) {
        this.itemEntityRepository = itemEntityRepository;
    }
}
