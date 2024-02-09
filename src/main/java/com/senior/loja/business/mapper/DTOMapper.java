package com.senior.loja.business.mapper;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public interface DTOMapper <D, DTO> {
    default Optional<D> toDomain(Optional<DTO> dto) {
        return dto.isEmpty() ? Optional.empty() : toDomain(dto.get());
    }

    Optional<D> toDomain(DTO dto);

    default Set<D> toDomain(Collection<DTO> dtos) {
        return dtos == null ? Collections.emptySet() : dtos.stream()
                .map(this::toDomain)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }

    DTO toDTO(D domain);

    default Set<DTO> toDTO(Collection<D> domains) {
        return domains == null ? Collections.emptySet() : domains.stream()
                .map(this::toDTO)
                .collect(Collectors.toSet());
    }
}
