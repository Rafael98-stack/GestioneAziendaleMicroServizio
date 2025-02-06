package com.azienda.dipendenti.mappers;

import com.azienda.dipendenti.dto.request.DipendenteRequest;
import com.azienda.dipendenti.dto.responses.DipendenteResponse;
import com.azienda.dipendenti.entities.Dipendente;
import org.springframework.stereotype.Service;

@Service
public class DipendenteMapper {

    public DipendenteResponse toResponse(Dipendente dipendente){
        return DipendenteResponse
                .builder()
                .id(dipendente.getId())
                .nome(dipendente.getNome())
                .cognome(dipendente.getCognome())
                .dataNascita(dipendente.getData_nascita())
                .email(dipendente.getEmail())
                .luogoNascita(dipendente.getLuogo_nascita())
                .telefono(dipendente.getTelefono())
                .immagineProfilo(dipendente.getImmagine_profilo())
                .build();
    }

    public Dipendente toEntity(DipendenteRequest request){
        return Dipendente
                .builder()
                .nome(request.nome())
                .cognome(request.cognome())
                .email(request.email())
                .data_nascita(request.dataNascita())
                .luogo_nascita(request.luogoNascita())
                .telefono(request.telefono())
                .immagine_profilo(request.immagineProfilo())
                .build();
    }
}
