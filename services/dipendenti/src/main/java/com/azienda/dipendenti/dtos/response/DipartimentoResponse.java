package com.azienda.dipendenti.dtos.response;

import lombok.Builder;

import java.util.List;

@Builder
public record DipartimentoResponse(
        Long id,
        String nome,
        String descrizione,
        List<Long> dipendenti,
        List<Long> posizioni
) {
}
