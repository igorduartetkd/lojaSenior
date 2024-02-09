package com.senior.loja.domain.repository;

import com.senior.loja.domain.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepository extends BaseDomainRepository<Item> {
    Page<Item> findAll(Pageable pageable);
}
