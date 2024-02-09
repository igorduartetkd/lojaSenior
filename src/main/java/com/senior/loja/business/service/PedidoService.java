package com.senior.loja.business.service;

import com.senior.loja.business.dto.AbrirFecharPedidoDTO;
import com.senior.loja.business.dto.AdicionarRemoverItemDTO;
import com.senior.loja.business.dto.DescontoDTO;
import com.senior.loja.business.dto.PedidoDTO;
import com.senior.loja.business.exception.ItemNotFoundException;
import com.senior.loja.business.exception.PedidoNotFoundException;
import com.senior.loja.business.mapper.PedidoDTOMapper;
import com.senior.loja.domain.entity.Item;
import com.senior.loja.domain.entity.ItemDoPedido;
import com.senior.loja.domain.entity.Pedido;
import com.senior.loja.domain.repository.ItemDoPedidoRepository;
import com.senior.loja.domain.repository.ItemRepository;
import com.senior.loja.domain.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    PedidoRepository pedidoRepository;
    ItemRepository itemRepository;
    ItemDoPedidoRepository itemDoPedidoRepository;
    PedidoDTOMapper pedidoDTOMapper;

    public PedidoService(PedidoRepository pedidoRepository,
                         ItemRepository itemRepository,
                         ItemDoPedidoRepository itemDoPedidoRepository,
                         PedidoDTOMapper pedidoDTOMapper) {
        this.pedidoRepository = pedidoRepository;
        this.itemRepository = itemRepository;
        this.itemDoPedidoRepository = itemDoPedidoRepository;
        this.pedidoDTOMapper = pedidoDTOMapper;
    }


    public Set<PedidoDTO> findAll() {
        return pedidoRepository.findAll().stream()
                .map(pedidoDTOMapper::toDTO)
                .collect(Collectors.toSet());
    }

    public PedidoDTO findById(UUID id) {
        return findPedidoComItensById(id)
                .map(pedidoDTOMapper::toDTO)
                .orElseThrow(() -> new PedidoNotFoundException(id));
    }

    private Optional<Pedido> findPedidoComItensById(UUID id) {
        return pedidoRepository.findById(id)
                .map(pedido -> {
                    pedido.setItens(itemDoPedidoRepository.findAllByPedido(pedido));
                    return pedido;
                });
    }

    public PedidoDTO create(PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoDTOMapper.toDomain(pedidoDTO).orElse(null);
        return pedidoRepository.save(pedido)
                .map(pedidoDTOMapper::toDTO)
                .orElse(null);
    }

    public PedidoDTO aplicarDesconto(DescontoDTO descontoDTO) {
        Pedido pedido = findPedidoComItensById(descontoDTO.getId())
                .map(pedidoOriginal -> {
                    pedidoOriginal.aplicarDesconto(descontoDTO.getPercentualDeDesconto());
                    pedidoRepository.save(pedidoOriginal);
                    return pedidoOriginal;
                })
                .orElseThrow(() -> new PedidoNotFoundException(descontoDTO.getId()));
        return pedidoDTOMapper.toDTO(pedido);
    }

    public PedidoDTO adicionarItem(AdicionarRemoverItemDTO adicionarRemoverItemDTO) {
        UUID idItem = adicionarRemoverItemDTO.getIdItem();
        UUID idPedido = adicionarRemoverItemDTO.getId();
        Item item = itemRepository.findById(idItem)
                .orElseThrow(() -> new ItemNotFoundException(idItem));

        Pedido pedido = findPedidoComItensById(idPedido)
                .map(pedidoOriginal -> {
                    ItemDoPedido itemDoPedido = pedidoOriginal.adicionarItem(item);
                    itemDoPedidoRepository.save(itemDoPedido);
                    pedidoRepository.save(pedidoOriginal);
                    return pedidoOriginal;
                })
                .orElseThrow(() -> new PedidoNotFoundException(idPedido));
        return pedidoDTOMapper.toDTO(pedido);
    }

    public PedidoDTO removerItem(AdicionarRemoverItemDTO adicionarRemoverItemDTO) {
        UUID idItem = adicionarRemoverItemDTO.getIdItem();
        UUID idPedido = adicionarRemoverItemDTO.getId();
        Item item = itemRepository.findById(idItem)
                .orElseThrow(() -> new ItemNotFoundException(idItem));

        Pedido pedido = findPedidoComItensById(idPedido)
                .map(pedidoOriginal -> {
                    ItemDoPedido itemDoPedido = pedidoOriginal.removerItem(item);
                    if (itemDoPedido.qtdChegouAZero()) {
                        itemDoPedidoRepository.delete(itemDoPedido);
                    } else {
                        itemDoPedidoRepository.save(itemDoPedido);
                    }
                    pedidoRepository.save(pedidoOriginal);
                    return pedidoOriginal;
                })
                .orElseThrow(() -> new PedidoNotFoundException(idPedido));
        return pedidoDTOMapper.toDTO(pedido);
    }

    public PedidoDTO abrirPedido(AbrirFecharPedidoDTO abrirFecharPedidoDTO) {
        Pedido pedido = pedidoRepository.findById(abrirFecharPedidoDTO.getId())
                .flatMap(pedidoOriginal -> {
                    pedidoOriginal.abrirPedido();
                    return pedidoRepository.save(pedidoOriginal);
                })
                .orElseThrow(() -> new PedidoNotFoundException(abrirFecharPedidoDTO.getId()));
        return pedidoDTOMapper.toDTO(pedido);
    }

    public PedidoDTO fecharPedido(AbrirFecharPedidoDTO abrirFecharPedidoDTO) {
        Pedido pedido = pedidoRepository.findById(abrirFecharPedidoDTO.getId())
                .flatMap(pedidoOriginal -> {
                    pedidoOriginal.fecharPedido();
                    return pedidoRepository.save(pedidoOriginal);
                })
                .orElseThrow(() -> new PedidoNotFoundException(abrirFecharPedidoDTO.getId()));
        return pedidoDTOMapper.toDTO(pedido);
    }
}
