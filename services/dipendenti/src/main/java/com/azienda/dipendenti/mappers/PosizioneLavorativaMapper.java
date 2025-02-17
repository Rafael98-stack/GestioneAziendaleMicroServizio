package com.azienda.dipendenti.mappers;

import com.azienda.dipendenti.dtos.request.PosizioneLavorativaRequestInsert;
import com.azienda.dipendenti.dtos.response.PosizioneLavorativaResponse;
import com.azienda.dipendenti.entities.PosizioneLavorativa;
import com.azienda.dipendenti.repositories.DipartimentoRepository;
import com.azienda.dipendenti.services.DipartimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PosizioneLavorativaMapper {

    @Autowired
    DipartimentoRepository dipartimentoRepository;

    @Autowired
    DipartimentoService dipartimentoService;

    public PosizioneLavorativa fromPosizioneLavorativaRequest(PosizioneLavorativaRequestInsert request) throws Exception {
        if (request.dipartimento()== null){
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
                .dipartimento(dipartimentoService.getDipartimentoById(request.dipartimento()))
                .build();
    }

    public PosizioneLavorativaResponse toResponse(PosizioneLavorativa posizioneLavorativa){
        return PosizioneLavorativaResponse
                .builder()
                .id(posizioneLavorativa.getId())
                .nome(posizioneLavorativa.getNome())
                .descrizione(posizioneLavorativa.getDescrizione())
                .build();
    }
}
