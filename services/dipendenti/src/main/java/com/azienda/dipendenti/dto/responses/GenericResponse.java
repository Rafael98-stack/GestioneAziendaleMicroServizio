package com.azienda.dipendenti.dto.responses;

import lombok.Builder;

@Builder
public record GenericResponse(
        String message
) {
}
