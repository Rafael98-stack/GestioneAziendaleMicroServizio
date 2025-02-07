package com.azienda.dipendenti.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record DipendenteUpdateRequest(
        String nome,
        String cognome,
        @Email
        String email,
        @Past
        LocalDate dataNascita,
        String luogoNascita,
        String telefono,
        String immagineProfilo,
        Long dipartimento_id
) {
}
