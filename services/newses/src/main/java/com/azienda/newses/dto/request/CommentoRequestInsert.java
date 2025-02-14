package com.azienda.newses.dto.request;

public record CommentoRequestInsert(

        String contenuto,

        Long id_dipendente,

        Long id_newse
) {
}
