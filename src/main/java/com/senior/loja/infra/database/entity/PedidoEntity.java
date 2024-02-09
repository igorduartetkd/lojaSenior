package com.senior.loja.infra.database.entity;

import com.senior.loja.domain.entity.Pedido;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pedido")
@SuperBuilder
@NoArgsConstructor
@Getter
public class PedidoEntity extends BaseEntity {

    @Transient
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ItemDoPedidoEntity> itens = new HashSet<>();

    @Column
    private BigDecimal desconto = BigDecimal.ZERO;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Pedido.Situacao situacao;
}
