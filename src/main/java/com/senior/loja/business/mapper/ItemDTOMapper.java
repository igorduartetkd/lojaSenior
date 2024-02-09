package com.senior.loja.business.mapper;

import com.senior.loja.business.dto.ItemDTO;
import com.senior.loja.domain.entity.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemDTOMapper {

    public Item toDomain(ItemDTO itemDTO) {
        return itemDTO == null ? null : Item.builder()
                .id(itemDTO.getId())
                .nome(itemDTO.getNome())
                .preco(itemDTO.getPreco())
                .ativo(itemDTO.getAtivo())
                .tipo(itemDTO.getTipo())
                .build();
    }

    public ItemDTO toDTO(Item item) {
        return item == null ? null : ItemDTO.builder()
                .id(item.getId())
                .nome(item.getNome())
                .preco(item.getPreco())
                .ativo(item.getAtivo())
                .tipo(item.getTipo())
                .build();
    }
}
