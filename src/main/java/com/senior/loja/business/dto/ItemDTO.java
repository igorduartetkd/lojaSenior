package com.senior.loja.business.dto;

import com.senior.loja.domain.entity.Item;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
@Builder
public class ItemDTO {
    UUID id;

    @NotBlank(message = "Não deve ser nulo")
    @Size(max = 100, message = "Não deve ter mais de 100 caracteres")
    String nome;

    @NotNull(message = "Não deve ser nulo")
    @DecimalMin(value = "0", inclusive = false, message = "Deve ser positivo")
    BigDecimal preco;

    @NotNull(message = "Não deve ser nulo")
    Boolean ativo;


    @NotNull(message = "Não deve ser nulo")
    Item.Tipo tipo;
}
