package com.senior.loja.infra.database.entity;

import com.senior.loja.domain.entity.Item;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Table(name = "item")
@SuperBuilder
@NoArgsConstructor
public class ItemEntity extends BaseEntity {
    @Column(length = 100)
    private String nome;

    @Column(precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    private Item.Tipo tipo;
}
