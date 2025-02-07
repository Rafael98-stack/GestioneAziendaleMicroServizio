package com.azienda.dipartimenti.dto.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.List;
@Builder
public record DipartimentoRequest(
        @NotBlank
        String nome,
        @NotBlank
        String descrizione,
        List<Long> posizioniLavorative
) {
}
