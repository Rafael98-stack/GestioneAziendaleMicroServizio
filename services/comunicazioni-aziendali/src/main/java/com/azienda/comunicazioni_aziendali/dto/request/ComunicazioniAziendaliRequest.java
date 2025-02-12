package com.azienda.comunicazioni_aziendali.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record ComunicazioniAziendaliRequest(

        @NotBlank
        String titolo,
        @NotBlank
        String contenuto,
        Long id_dipendente
)
{

}
