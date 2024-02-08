package com.senior.loja.infra.database.repository;

import com.senior.loja.infra.database.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface BaseEntityRepository <E extends BaseEntity> extends JpaRepository<E, UUID> {
}
