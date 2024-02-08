package com.senior.loja.infra.mapper;

import com.senior.loja.domain.entity.BaseDomain;
import com.senior.loja.infra.database.entity.BaseEntity;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public interface EntityToDomainMapper<E extends BaseEntity, D extends BaseDomain> {
    default Optional<D> toDomain(Optional<E> entity) {
        return entity.isEmpty() ? Optional.empty() : toDomain(entity.get());
    }

    Optional<D> toDomain(E entity);

    default Set<D> toDomain(Collection<E> entities) {
        return entities == null ? Collections.emptySet() : entities.stream()
                .map(this::toDomain)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }
}
