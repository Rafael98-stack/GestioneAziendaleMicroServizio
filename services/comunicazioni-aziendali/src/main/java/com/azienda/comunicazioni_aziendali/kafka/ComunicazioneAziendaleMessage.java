package com.azienda.comunicazioni_aziendali.kafka;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ComunicazioneAziendaleMessage(
        Long id,
        String titolo,
        String contenuto,
        Long id_dipendente,
        LocalDateTime timestamp
) {
}
