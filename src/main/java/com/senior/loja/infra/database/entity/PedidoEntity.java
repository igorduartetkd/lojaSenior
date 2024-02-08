package com.senior.loja.infra.database.entity;

import com.senior.loja.domain.entity.Pedido;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pedido")
@SuperBuilder
@NoArgsConstructor
public class PedidoEntity extends BaseEntity {

    @OneToMany(mappedBy = "item_do_pedido")
    private Set<ItemDoPedidoEntity> itens = new HashSet<>();

    @Column
    private BigDecimal desconto;

    @Enumerated(EnumType.STRING)
    private Pedido.Situacao situacao;
}
