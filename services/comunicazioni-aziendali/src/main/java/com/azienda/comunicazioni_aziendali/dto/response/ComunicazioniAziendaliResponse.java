package com.azienda.comunicazioni_aziendali.dto.response;

import lombok.Builder;

@Builder
public record ComunicazioniAziendaliResponse (
        Long Id,
        String titolo,
        String contenuto,
        Long id_dipendente)
{

}
