package com.senior.loja.domain.repository;

import com.senior.loja.domain.entity.BaseDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface BaseDomainRepository<D extends BaseDomain> {
    Optional<D> save(D domain);

    Optional<D> findById(UUID id);

    Set<D> findAll();



    void delete(D domain);
}
