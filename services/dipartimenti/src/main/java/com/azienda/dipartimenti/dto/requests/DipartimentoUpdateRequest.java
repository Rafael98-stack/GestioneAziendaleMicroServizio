package com.azienda.dipartimenti.dto.requests;

import lombok.Builder;

import java.util.List;
@Builder
public record DipartimentoUpdateRequest(
        String nome,
        String descrizione,
        List<Long> posizioniLavorative
) {
}
