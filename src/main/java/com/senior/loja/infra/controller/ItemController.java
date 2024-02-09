package com.senior.loja.infra.controller;

import com.senior.loja.business.dto.ItemDTO;
import com.senior.loja.business.service.ItemService;
import com.senior.loja.infra.assembler.ItemModelAssembler;
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
@RequestMapping("itens")
public class ItemController {

    ItemService itemService;
    ItemModelAssembler itemModelAssembler;

    public ItemController(ItemService itemService, ItemModelAssembler itemModelAssembler) {
        this.itemService = itemService;
        this.itemModelAssembler = itemModelAssembler;
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findItemById(@PathVariable UUID id) {
        ItemDTO item = itemService.findById(id);
        EntityModel<ItemDTO> entityModel = itemModelAssembler.toModel(item);
        return ResponseEntity.ok(entityModel);
    }

    @GetMapping
    public ResponseEntity<?> findAll(
            @PageableDefault(size = 5, page = 0) Pageable pageable
            ) {
        Page<ItemDTO> itens = itemService.findAll(pageable);
        return ResponseEntity.ok(itens);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ItemDTO itemDTO) {
        ItemDTO item = itemService.create(itemDTO);
        EntityModel<ItemDTO> entityModel = itemModelAssembler.toModel(item);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id,
                                    @RequestBody @Valid ItemDTO itemDTO) {
        ItemDTO item = itemService.update(id, itemDTO);
        EntityModel<ItemDTO> entityModel = itemModelAssembler.toModel(item);
        return ResponseEntity.ok(entityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
