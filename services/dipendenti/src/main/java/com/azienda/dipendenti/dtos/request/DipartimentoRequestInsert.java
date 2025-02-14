package com.azienda.dipendenti.dtos.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record DipartimentoRequestInsert(
        @NotBlank(message = "Il nome del dipartimento non può essere blank o null")
        String nome,
        @NotBlank(message = "La descrizione del dipartimento non può essere blank o null")
        String descrizione,
        List<Long> id_posizione_lavorativa
) {
}
