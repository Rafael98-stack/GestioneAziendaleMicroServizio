package com.azienda.dipendenti.services;

import com.azienda.dipendenti.dto.request.DipendenteRequest;
import com.azienda.dipendenti.dto.request.DipendenteUpdateRequest;
import com.azienda.dipendenti.dto.responses.DipendenteResponse;
import com.azienda.dipendenti.dto.responses.EntityIdResponse;
import com.azienda.dipendenti.dto.responses.GenericResponse;
import com.azienda.dipendenti.entities.Dipendente;
import com.azienda.dipendenti.exceptions.DipendenteNotFoundException;
import com.azienda.dipendenti.mappers.DipendenteMapper;
import com.azienda.dipendenti.repositories.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DipendenteService {
    @Autowired
    DipendenteRepository dipendenteRepository;
    @Autowired
    DipendenteMapper dipendenteMapper;

    public DipendenteResponse getDipendenteById(Long id){
        Dipendente dipendente = dipendenteRepository
                .findById(id)
                .orElseThrow(() -> new DipendenteNotFoundException(String.format("Il dipendente con id %d non esiste", id)));
        return  dipendenteMapper.toResponse(dipendente);
    }

    public List<DipendenteResponse> getAll(){
        return  dipendenteRepository
                .findAll()
                .stream()
                .map(dipendenteMapper::toResponse)
                .toList();
    }

    public EntityIdResponse createDipendente(DipendenteRequest request){
        Dipendente dipendente = dipendenteRepository.save(dipendenteMapper.toEntity(request));
        return EntityIdResponse
                .builder()
                .id(dipendente.getId())
                .build();
    }

    public EntityIdResponse updateDipendente(Long id, DipendenteUpdateRequest request){
        Dipendente dipendente = dipendenteRepository
                .findById(id)
                .orElseThrow(() -> new DipendenteNotFoundException(String.format("Il dipendente con id %d non esiste", id)));
        if (request.nome() != null) dipendente.setNome(request.nome());
        if (request.cognome() != null) dipendente.setCognome(request.cognome());
        if (request.email() != null) dipendente.setEmail(request.email());
        if (request.dataNascita() != null) dipendente.setData_nascita(request.dataNascita());
        if (request.luogoNascita() != null) dipendente.setLuogo_nascita(request.luogoNascita());
        if (request.telefono() != null) dipendente.setTelefono(request.telefono());
        if (request.immagineProfilo() != null) dipendente.setImmagine_profilo(request.immagineProfilo());
        return EntityIdResponse
                .builder()
                .id(dipendente.getId())
                .build();
    }

    public GenericResponse deleteDipendenteById(Long id){
        dipendenteRepository.deleteById(id);
        return GenericResponse
                .builder()
                .message(String.format("Il dipendente con id %d è stato eliminato", id))
                .build();
    }
}
