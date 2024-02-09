package com.senior.loja.infra.assembler;

import com.senior.loja.business.dto.ItemDTO;
import com.senior.loja.infra.controller.ItemController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ItemModelAssembler implements RepresentationModelAssembler<ItemDTO, EntityModel<ItemDTO>> {
    @Override
    public EntityModel<ItemDTO> toModel(ItemDTO item) {
        return EntityModel.of(item,
                linkTo(methodOn(ItemController.class).findItemById(item.getId())).withSelfRel(),
                linkTo(methodOn(ItemController.class).findAll()).withRel("itens"));
    }

    @Override
    public CollectionModel<EntityModel<ItemDTO>> toCollectionModel(Iterable<? extends ItemDTO> itens) {
        return RepresentationModelAssembler.super.toCollectionModel(itens);
    }
}
