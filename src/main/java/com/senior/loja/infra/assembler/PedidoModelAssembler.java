package com.senior.loja.infra.assembler;

import com.senior.loja.business.dto.PedidoDTO;
import com.senior.loja.infra.controller.PedidoController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PedidoModelAssembler implements RepresentationModelAssembler<PedidoDTO, EntityModel<PedidoDTO>> {
    @Override
    public EntityModel<PedidoDTO> toModel(PedidoDTO pedido) {
        return EntityModel.of(pedido,
                linkTo(methodOn(PedidoController.class).findById(pedido.getId())).withSelfRel(),
                linkTo(methodOn(PedidoController.class).findAll()).withRel("pedidos"));
    }

    @Override
    public CollectionModel<EntityModel<PedidoDTO>> toCollectionModel(Iterable<? extends PedidoDTO> pedidos) {
        return RepresentationModelAssembler.super.toCollectionModel(pedidos);
    }
}
