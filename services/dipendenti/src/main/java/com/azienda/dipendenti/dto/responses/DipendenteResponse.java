package com.azienda.dipendenti.dto.responses;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record DipendenteResponse(
        Long id,
        String nome,
        String cognome,
        String email,
        LocalDate dataNascita,
        String luogoNascita,
        String telefono,
        String immagineProfilo
) {
}
