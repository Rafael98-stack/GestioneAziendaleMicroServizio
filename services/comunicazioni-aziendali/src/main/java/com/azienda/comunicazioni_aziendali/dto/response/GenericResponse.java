package com.azienda.comunicazioni_aziendali.dto.response;

import lombok.Builder;

@Builder
public record GenericResponse(
        String message
) {
}
