package com.azienda.dipendenti.mappers;

import com.azienda.dipendenti.dtos.request.DipartimentoRequestInsert;
import com.azienda.dipendenti.entities.Dipartimento;
import com.azienda.dipendenti.repositories.PosizioneLavorativaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class DipartimentoMapper {

    @Autowired
    PosizioneLavorativaRepository posizioneLavorativaRepository;

    public Dipartimento fromDipartimentoRequestInsert(DipartimentoRequestInsert dipartimentoRequestInsert){

        return Dipartimento
                .builder()
                .nome(dipartimentoRequestInsert.nome())
                .descrizione(dipartimentoRequestInsert.descrizione())
                .posizioniLavorative(dipartimentoRequestInsert.id_posizione_lavorativa().stream().map(id -> {
                    try {
                        return posizioneLavorativaRepository.findById(id)
                                .orElseThrow(()-> new Exception("Posizione lavorativa con id " + dipartimentoRequestInsert.id_posizione_lavorativa() + " non trovato"));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toList()))
                .build();
    }
}
