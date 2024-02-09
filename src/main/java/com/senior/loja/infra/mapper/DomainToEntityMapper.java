package com.senior.loja.infra.mapper;

import com.senior.loja.domain.entity.BaseDomain;
import com.senior.loja.infra.database.entity.BaseEntity;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public interface DomainToEntityMapper<D extends BaseDomain, E extends BaseEntity> {
    E toEntity(D domain);

    default Set<E> toEntity(Collection<D> domains) {
        return domains == null ? Collections.emptySet() : domains.stream()
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }
}
