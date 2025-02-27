package com.azienda.posizioni_lavorative.dtos.PosizioneLavorativaDtos;

import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record PosizioneLavorativaUpdate(
        @NotBlank
        String nome,
        @NotBlank
        String descrizione,
        Set<Long> dipartimenti,
        Long id_dipendente
) {
}
