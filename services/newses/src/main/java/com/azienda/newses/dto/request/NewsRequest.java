package com.azienda.newses.dto.request;

import lombok.Builder;

@Builder
public record NewsRequest(
        String titolo,
        String contenuto,
        Long  id_dipendente,
        String immagine,
        Long like
)
{

}
