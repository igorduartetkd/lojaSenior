package com.senior.loja.infra.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "item_do_pedido")
@SuperBuilder
@NoArgsConstructor
@Getter
public class ItemDoPedidoEntity extends BaseEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private ItemEntity itemEntity;

    @NotNull
    @Column
    private Integer qtd;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private PedidoEntity pedido;
}
