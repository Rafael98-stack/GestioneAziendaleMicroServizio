package com.azienda.dipendenti.dtos.response;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record TimbraturaResponse(
        Long id,
        LocalDate dataCorrente,
        LocalDateTime orarioEntrata,
        LocalDateTime inizioPranzo,
        LocalDateTime finePranzo,
        LocalDateTime uscite,
        Long idDipendente
)
{

}
