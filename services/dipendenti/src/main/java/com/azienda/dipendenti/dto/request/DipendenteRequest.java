package com.azienda.dipendenti.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record DipendenteRequest(
        @NotBlank(message = "Il nome non può essere vuoto")
        String nome,
        @NotBlank(message = "Il cognome non può essere vuoto")
        String cognome,
        @NotBlank(message = "L'email non può essere vuota")
        @Email
        String email,
        @NotNull(message = "La data di nascita non può essere null")
        @Past
        LocalDate dataNascita,
        @NotBlank(message = "Il luogo di nascita non può essere vuoto")
        String luogoNascita,
        @NotBlank(message = "Il numero di telefono non può essere vuoto")
        String telefono,
        String immagineProfilo
        ) {
}
