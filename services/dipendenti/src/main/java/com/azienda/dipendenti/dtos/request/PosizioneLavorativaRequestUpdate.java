package com.azienda.dipendenti.dtos.request;

import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record PosizioneLavorativaRequestUpdate(
        @NotBlank
        String nome,
        @NotBlank
        String descrizione,
        Set<Long> dipartimenti,
        Long id_dipendente
) {
}
