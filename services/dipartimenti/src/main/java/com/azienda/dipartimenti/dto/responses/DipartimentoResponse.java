package com.azienda.dipartimenti.dto.responses;

import lombok.Builder;

import java.util.List;

@Builder
public record DipartimentoResponse(
        Long id,
        String nome,
        String descrizione,
        List<Long> posizioniLavorative
) {
}
