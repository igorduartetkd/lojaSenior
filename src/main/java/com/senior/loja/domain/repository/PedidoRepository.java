package com.senior.loja.domain.repository;

import com.senior.loja.domain.entity.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidoRepository extends BaseDomainRepository<Pedido> {
    Page<Pedido> findAll(Pageable pageable);
}
