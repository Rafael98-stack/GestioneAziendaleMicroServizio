package com.azienda.newses;

import lombok.Builder;

@Builder
public record ComunicazioniAziendaliResponse (
        Long Id,
        String titolo,
        String contenuto,
        Long id_dipendente)
{

}
