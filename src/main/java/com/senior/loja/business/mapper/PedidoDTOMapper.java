package com.senior.loja.business.mapper;

import com.senior.loja.business.dto.PedidoDTO;
import com.senior.loja.domain.entity.Pedido;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PedidoDTOMapper implements DTOMapper<Pedido, PedidoDTO> {

    ItemDoPedidoDTOMapper itemDoPedidoDTOMapper;

    public PedidoDTOMapper(ItemDoPedidoDTOMapper itemDoPedidoDTOMapper) {
        this.itemDoPedidoDTOMapper = itemDoPedidoDTOMapper;
    }

    public Optional<Pedido> toDomain(PedidoDTO pedidoDTO) {
        return pedidoDTO == null ? Optional.empty() : Optional.of(Pedido.builder()
                .id(pedidoDTO.getId())
                .desconto(pedidoDTO.getDesconto())
                .situacao(pedidoDTO.getSituacao())
                .itens(itemDoPedidoDTOMapper.toDomain(pedidoDTO.getItens()))
                .build());
    }

    public PedidoDTO toDTO(Pedido pedido) {
        return pedido == null ? null : PedidoDTO.builder()
                .id(pedido.getId())
                .itens(itemDoPedidoDTOMapper.toDTO(pedido.getItens()))
                .desconto(pedido.getDesconto())
                .situacao(pedido.getSituacao())
                .valorTotal(pedido.obterValorComDesconto())
                .build();
    }


}
