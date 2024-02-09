package com.senior.loja.business.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class AdicionarRemoverItemDTO {
    @NotNull
    UUID id;

    @NotNull
    UUID idItem;


}
