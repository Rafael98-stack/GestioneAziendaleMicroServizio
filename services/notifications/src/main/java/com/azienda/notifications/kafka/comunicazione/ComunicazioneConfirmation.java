package com.azienda.notifications.kafka.comunicazione;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ComunicazioneConfirmation(
        Long id,
        String titolo,
        String contenuto,
        Long id_dipendente,
        LocalDateTime timestamp
) {
}