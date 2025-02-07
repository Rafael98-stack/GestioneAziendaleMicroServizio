package com.azienda.posizioni_lavorative.mappers;


import com.azienda.posizioni_lavorative.dtos.PosizioneLavorativaDtos.PosizioneLavorativaRequest;
import com.azienda.posizioni_lavorative.entities.PosizioneLavorativa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PosizioneLavorativaMapper {

    @Autowired
    DipartimentoRepository dipartimentoRepository;

    public PosizioneLavorativa fromPosizioneLavorativaRequest(PosizioneLavorativaRequest request){
        if (request.dipartimenti()== null){
            return PosizioneLavorativa
                    .builder()
                    .nome(request.nome())
                    .descrizione(request.descrizione())
                    .build();
        }
        return PosizioneLavorativa
                .builder()
                .nome(request.nome())
                .descrizione(request.descrizione())
                .dipartimenti(request.dipartimenti().stream().map(id ->{
                    try {
                        return dipartimentoRepository.findById(id)
                                .orElseThrow(()-> new DipartimentoNotFoundException("Dipartimenti non trovati"));
                    } catch (RuntimeException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toSet()))
                .build();
    }
}
