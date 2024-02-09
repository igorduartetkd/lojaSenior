package com.senior.loja.business.service;

import com.senior.loja.business.dto.ItemDTO;
import com.senior.loja.business.exception.ItemConflictException;
import com.senior.loja.business.exception.ItemNotFoundException;
import com.senior.loja.business.mapper.ItemDTOMapper;
import com.senior.loja.domain.entity.Item;
import com.senior.loja.domain.repository.ItemDoPedidoRepository;
import com.senior.loja.domain.repository.ItemRepository;
import com.senior.loja.infra.repository.ItemDoPedidoRepositoryImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ItemService {

    ItemRepository itemRepository;
    ItemDoPedidoRepository itemDoPedidoRepository;
    ItemDTOMapper itemDTOMapper;

    public ItemService(ItemRepository itemRepository, ItemDoPedidoRepositoryImpl itemDoPedidoRepository, ItemDTOMapper itemDTOMapper) {
        this.itemRepository = itemRepository;
        this.itemDoPedidoRepository = itemDoPedidoRepository;
        this.itemDTOMapper = itemDTOMapper;
    }

    public ItemDTO findById(UUID id) {
        return itemRepository.findById(id)
                .map(itemDTOMapper::toDTO)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }

    public Set<ItemDTO> findAll() {
        return itemRepository.findAll().stream()
                .map(itemDTOMapper::toDTO)
                .collect(Collectors.toSet());
    }

    public Page<ItemDTO> findAll(Pageable pageable) {
        return itemRepository.findAll(pageable)
                .map(itemDTOMapper::toDTO);
    }

    public ItemDTO create(ItemDTO itemDTO) {
        Item item = itemDTOMapper.toDomain(itemDTO);
        return itemRepository.save(item)
                .map(itemDTOMapper::toDTO)
                .orElse(null);
    }

    public ItemDTO update(UUID id, ItemDTO itemDTO) {
        Item item = itemRepository.findById(id)
                .map(itemOriginal -> {
                    itemOriginal.setNome(itemDTO.getNome());
                    itemOriginal.setPreco(itemDTO.getPreco());
                    itemOriginal.setAtivo(itemDTO.getAtivo());
                    itemOriginal.setTipo(itemDTO.getTipo());
                    return itemRepository.save(itemOriginal).orElse(null);
                })
                .orElseThrow(() -> new ItemNotFoundException(id));

        return itemDTOMapper.toDTO(item);
    }

    public void delete(UUID id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
        Boolean existePedidoComEsteItem = itemDoPedidoRepository.existsByItemId(id);
        if (existePedidoComEsteItem) {
            throw new ItemConflictException(id);
        }
        itemRepository.delete(item);
    }

}
