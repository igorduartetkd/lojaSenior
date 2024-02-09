package com.senior.loja.infra.database.entity;

import com.senior.loja.domain.entity.Item;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Table(name = "item")
@SuperBuilder
@NoArgsConstructor
@Getter
public class ItemEntity extends BaseEntity {

    @NotNull
    @Column(nullable = false, length = 100)
    private String nome;

    @NotNull
    @Column(precision = 10, scale = 2)
    private BigDecimal preco;

    @NotNull
    private Boolean ativo;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Item.Tipo tipo;
}
