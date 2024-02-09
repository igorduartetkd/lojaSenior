package com.senior.loja.infra.repository;

import com.senior.loja.domain.entity.Pedido;
import com.senior.loja.domain.repository.PedidoRepository;
import com.senior.loja.infra.database.entity.PedidoEntity;
import com.senior.loja.infra.database.repository.PedidoEntityRepository;
import com.senior.loja.infra.mapper.PedidoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Component
public class PedidoRepositoryImpl implements PedidoRepository {

    PedidoEntityRepository pedidoEntityRepository;
    PedidoMapper pedidoMapper;

    public PedidoRepositoryImpl(PedidoEntityRepository pedidoEntityRepository,
                                PedidoMapper pedidoMapper) {
        this.pedidoEntityRepository = pedidoEntityRepository;
        this.pedidoMapper = pedidoMapper;
    }

    @Override
    public Optional<Pedido> save(Pedido domain) {
        PedidoEntity pedidoEntity = pedidoMapper.toEntity(domain);
        pedidoEntity = pedidoEntityRepository.save(pedidoEntity);
        return pedidoMapper.toDomainOptional(pedidoEntity);
    }

    @Override
    public Optional<Pedido> findById(UUID id) {
        Optional<PedidoEntity> pedidoEntity = pedidoEntityRepository.findById(id);
        return pedidoMapper.toDomainOptional(pedidoEntity);
    }

    public Set<Pedido> findAll() {
        List<PedidoEntity> pedidos = pedidoEntityRepository.findAll();
        return pedidoMapper.toDomainOptional(pedidos);
    }

    public Page<Pedido> findAll(Pageable pageable) {
        Page<PedidoEntity> pedidos = pedidoEntityRepository.findAll(pageable);
        return pedidos.map(pedidoMapper::toDomain);
    }


    @Override
    public void delete(Pedido domain) {
        pedidoEntityRepository.findById(domain.getId())
                .ifPresent(pedidoEntityRepository::delete);
    }


}
