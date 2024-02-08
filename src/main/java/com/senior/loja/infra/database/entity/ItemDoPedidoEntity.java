package com.senior.loja.infra.database.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "item_do_pedido")
@SuperBuilder
@NoArgsConstructor
public class ItemDoPedidoEntity extends BaseEntity {

    @OneToOne
    private ItemEntity itemEntity;

    @Column
    private Integer qtd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    private PedidoEntity pedido;
}
