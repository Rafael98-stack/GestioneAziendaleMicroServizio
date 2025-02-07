package com.azienda.dipartimenti.services;

import com.azienda.dipartimenti.dto.responses.DipartimentoResponse;
import com.azienda.dipartimenti.entities.Dipartimento;
import com.azienda.dipartimenti.mappers.DipartimentoMapper;
import com.azienda.dipartimenti.repositories.DipartimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DipartimentoService {

    @Autowired
    DipartimentoRepository dipartimentoRepository;

    @Autowired
    DipartimentoMapper dipartimentoMapper;

    private PosizioniClient posizioniClient;

    public DipartimentoResponse getDipartimentoById(Long id){
        Dipartimento dipartimento = dipartimentoRepository
                .findById(id)
                .orElseThrow();
        return dipartimentoMapper.toResponse(dipartimento);
    }

    public List<DipartimentoResponse> getAll(){
        return dipartimentoRepository
                .findAll()
                .stream()
                .map(dipartimentoMapper::toResponse)
                .toList();
    }
}
