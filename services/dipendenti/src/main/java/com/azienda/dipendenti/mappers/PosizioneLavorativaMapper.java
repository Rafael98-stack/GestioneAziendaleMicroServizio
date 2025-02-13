package com.azienda.dipendenti.mappers;

import com.azienda.dipendenti.dtos.request.PosizioneLavorativaRequestInsert;
import com.azienda.dipendenti.entities.PosizioneLavorativa;
import com.azienda.dipendenti.repositories.DipartimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PosizioneLavorativaMapper {

    @Autowired
    DipartimentoRepository dipartimentoRepository;

    public PosizioneLavorativa fromPosizioneLavorativaRequest(PosizioneLavorativaRequestInsert request){
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
                                .orElseThrow(()-> new Exception("Dipartimenti non trovati"));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toSet()))
                .build();
    }
}
