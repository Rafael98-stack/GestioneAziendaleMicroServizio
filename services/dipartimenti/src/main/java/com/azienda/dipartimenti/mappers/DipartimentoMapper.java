package com.azienda.dipartimenti.mappers;

import com.azienda.dipartimenti.dto.requests.DipartimentoRequest;
import com.azienda.dipartimenti.dto.responses.DipartimentoResponse;
import com.azienda.dipartimenti.entities.Dipartimento;
import org.springframework.stereotype.Service;

@Service
public class DipartimentoMapper {

    public DipartimentoResponse toResponse(Dipartimento dipartimento){
        return DipartimentoResponse
                .builder()
                .id(dipartimento.getId())
                .nome(dipartimento.getNome())
                .descrizione(dipartimento.getDescrizione())
                .posizioniLavorative(dipartimento.getPosizioniLavorative())
                .build();
    }

    public Dipartimento toEntity(DipartimentoRequest request){
        return Dipartimento
                .builder()
                .nome(request.nome())
                .descrizione(request.descrizione())
                .posizioniLavorative(request.posizioniLavorative())
                .build();
    }
}
