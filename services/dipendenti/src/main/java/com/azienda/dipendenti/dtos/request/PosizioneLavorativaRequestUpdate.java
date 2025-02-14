package com.azienda.dipendenti.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record PosizioneLavorativaRequestUpdate(
        @NotBlank
        String nome,
        @NotBlank
        String descrizione,
        Long dipartimento,
        Long id_dipendente
) {
}
