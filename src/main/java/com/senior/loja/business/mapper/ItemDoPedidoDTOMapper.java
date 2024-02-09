package com.senior.loja.business.mapper;

import com.senior.loja.business.dto.ItemDoPedidoDTO;
import com.senior.loja.domain.entity.ItemDoPedido;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ItemDoPedidoDTOMapper implements DTOMapper<ItemDoPedido, ItemDoPedidoDTO>{

    ItemDTOMapper itemDTOMapper;

    public ItemDoPedidoDTOMapper(ItemDTOMapper itemDTOMapper) {
        this.itemDTOMapper = itemDTOMapper;
    }

    public Optional<ItemDoPedido> toDomain(ItemDoPedidoDTO itemDoPedidoDTO) {
        return itemDoPedidoDTO == null ? Optional.empty() : Optional.of(ItemDoPedido.builder()
                .id(itemDoPedidoDTO.getId())
                .item(itemDTOMapper.toDomain(itemDoPedidoDTO.getItem()))
                .qtd(itemDoPedidoDTO.getQtd())
                .build());
    }

    public ItemDoPedidoDTO toDTO(ItemDoPedido itemDoPedido) {
        return itemDoPedido == null ? null : ItemDoPedidoDTO.builder()
                .id(itemDoPedido.getId())
                .item(itemDTOMapper.toDTO(itemDoPedido.getItem()))
                .qtd(itemDoPedido.getQtd())
                .build();
    }
}
