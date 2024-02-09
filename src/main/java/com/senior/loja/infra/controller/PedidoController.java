package com.senior.loja.infra.controller;

import com.senior.loja.business.dto.*;
import com.senior.loja.business.service.PedidoService;
import com.senior.loja.domain.entity.Pedido;
import com.senior.loja.infra.assembler.PedidoModelAssembler;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("pedidos")
public class PedidoController {

    PedidoService pedidoService;
    PedidoModelAssembler pedidoModelAssembler;

    public PedidoController(PedidoService pedidoService, PedidoModelAssembler pedidoModelAssembler) {
        this.pedidoService = pedidoService;
        this.pedidoModelAssembler = pedidoModelAssembler;
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        PedidoDTO pedido = pedidoService.findById(id);
        EntityModel<PedidoDTO> entityModel = pedidoModelAssembler.toModel(pedido);
        return ResponseEntity.ok(entityModel);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        Set<PedidoDTO> pedidos = pedidoService.findAll();
        CollectionModel<EntityModel<PedidoDTO>> collectionModel = pedidoModelAssembler.toCollectionModel(pedidos);
        return ResponseEntity.ok(collectionModel);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid PedidoDTO pedidoDTO) {
        PedidoDTO pedido = pedidoService.create(pedidoDTO);
        EntityModel<PedidoDTO> entityModel = pedidoModelAssembler.toModel(pedido);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }


    @PatchMapping(value = "/aplicarDesconto")
    public ResponseEntity<?> aplicarDesconto(@RequestBody @Valid DescontoDTO descontoDTO) {
        PedidoDTO pedido = pedidoService.aplicarDesconto(descontoDTO);
        EntityModel<PedidoDTO> entityModel = pedidoModelAssembler.toModel(pedido);
        return ResponseEntity.ok(entityModel);
    }

    @PatchMapping(value = "/adicionarItem")
    public ResponseEntity<?> adicionarItemAoPedido(@RequestBody @Valid AdicionarRemoverItemDTO adicionarRemoverItemDTO) {
        PedidoDTO pedido = pedidoService.adicionarItem(adicionarRemoverItemDTO);
        EntityModel<PedidoDTO> entityModel = pedidoModelAssembler.toModel(pedido);
        return ResponseEntity.ok(entityModel);
    }

    @PatchMapping(value = "/removerItem")
    public ResponseEntity<?> removerItemDoPedido(@RequestBody @Valid AdicionarRemoverItemDTO adicionarRemoverItemDTO) {
        PedidoDTO pedido = pedidoService.removerItem(adicionarRemoverItemDTO);
        EntityModel<PedidoDTO> entityModel = pedidoModelAssembler.toModel(pedido);
        return ResponseEntity.ok(entityModel);
    }

    @PatchMapping(value = "/abrirPedido")
    public ResponseEntity<?> abrirPedido(@RequestBody @Valid AbrirFecharPedidoDTO abrirFecharPedidoDTO) {
        PedidoDTO pedido = pedidoService.abrirPedido(abrirFecharPedidoDTO);
        EntityModel<PedidoDTO> entityModel = pedidoModelAssembler.toModel(pedido);
        return ResponseEntity.ok(entityModel);
    }

    @PatchMapping(value = "/fecharPedido")
    public ResponseEntity<?> fecharPedido(@RequestBody @Valid AbrirFecharPedidoDTO abrirFecharPedidoDTO) {
        PedidoDTO pedido = pedidoService.fecharPedido(abrirFecharPedidoDTO);
        EntityModel<PedidoDTO> entityModel = pedidoModelAssembler.toModel(pedido);
        return ResponseEntity.ok(entityModel);
    }
}
