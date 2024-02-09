package com.senior.loja.infra.database.repository;

import com.senior.loja.infra.database.entity.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemEntityRepository extends BaseEntityRepository<ItemEntity> {
}
