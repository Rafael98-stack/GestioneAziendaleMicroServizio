package com.azienda.dipendenti.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.Set;

@Builder
public record PosizioneLavorativaRequestInsert(
        @NotBlank(message = "Il nome non puo essere blank o null")
        String nome,
        @NotBlank(message = "La descrizione non puo essere blank o null")
        String descrizione,
        Set<Long> dipartimenti
) {
}
