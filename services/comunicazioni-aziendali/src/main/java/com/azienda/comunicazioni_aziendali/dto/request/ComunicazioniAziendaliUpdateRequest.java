package com.azienda.comunicazioni_aziendali.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record ComunicazioniAziendaliUpdateRequest(
        @NotBlank
        String titolo,
        @NotBlank
        String contenuto,
        Long id_dipendente
)
{

}
