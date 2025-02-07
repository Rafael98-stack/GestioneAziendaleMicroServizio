package com.azienda.dipartimenti.dto.responses;

import lombok.Builder;

@Builder
public record GenericResponse(
        String message
) {
}
