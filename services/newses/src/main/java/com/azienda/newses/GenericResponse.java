package com.azienda.newses;

import lombok.Builder;

@Builder
public record GenericResponse(
        String message
) {
}
