package com.azienda.newses.dto.response;

import lombok.Builder;

@Builder
public record NewsResponse(
        Long id,
        String titolo,
        String contenuto,
        String imagine){}
