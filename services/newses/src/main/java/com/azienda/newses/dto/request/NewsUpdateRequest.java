package com.azienda.newses.dto.request;

import lombok.Builder;

@Builder
public record NewsUpdateRequest (
        String titolo,
        String contenuto,
        Long  id_dipendente,
        String immagine){
}
