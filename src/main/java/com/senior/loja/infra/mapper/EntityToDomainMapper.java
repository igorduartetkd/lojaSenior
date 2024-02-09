package com.senior.loja.infra.mapper;

import com.senior.loja.domain.entity.BaseDomain;
import com.senior.loja.infra.database.entity.BaseEntity;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public interface EntityToDomainMapper<E extends BaseEntity, D extends BaseDomain> {
    default Optional<D> toDomainOptional(Optional<E> entity) {
        return entity.isEmpty() ? Optional.empty() : toDomainOptional(entity.get());
    }

    Optional<D> toDomainOptional(E entity);

    default D toDomain(E entity) {
        return toDomainOptional(entity).orElse(null);
    }

    default Set<D> toDomainOptional(Collection<E> entities) {
        return entities == null ? Collections.emptySet() : entities.stream()
                .map(this::toDomainOptional)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }
}
