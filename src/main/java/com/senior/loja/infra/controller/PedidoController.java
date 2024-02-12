package com.senior.loja.infra.controller;

import com.senior.loja.business.dto.AbrirFecharPedidoDTO;
import com.senior.loja.business.dto.AdicionarRemoverItemDTO;
import com.senior.loja.business.dto.DescontoDTO;
import com.senior.loja.business.dto.PedidoDTO;
import com.senior.loja.business.service.PedidoService;
import com.senior.loja.infra.assembler.PedidoModelAssembler;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> findAll(
            @PageableDefault(size = 5) Pageable pageable
    ) {
        Page<PedidoDTO> pedidos = pedidoService.findAll(pageable);
        return ResponseEntity.ok(pedidos);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
