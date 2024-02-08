package com.senior.loja.infra.repository;

import com.senior.loja.domain.repository.PedidoRepository;
import com.senior.loja.infra.database.repository.PedidoEntityRepository;
import org.springframework.stereotype.Component;

@Component
public class PedidoRepositoryImpl implements PedidoRepository {

    PedidoEntityRepository pedidoEntityRepository;

    public PedidoRepositoryImpl(PedidoEntityRepository pedidoEntityRepository) {
        this.pedidoEntityRepository = pedidoEntityRepository;
    }
}
