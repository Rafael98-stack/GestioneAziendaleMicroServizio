package com.azienda.comunicazioni_aziendali.services;

import com.azienda.comunicazioni_aziendali.dto.response.ComunicazioniAziendaliResponse;
import com.azienda.comunicazioni_aziendali.entities.ComunicazioniAziendali;
import com.azienda.comunicazioni_aziendali.exceptions.ComunicazioniAziendaliNotFoundException;
import com.azienda.comunicazioni_aziendali.mapper.ComunicazioniAziendaliMapper;
import com.azienda.comunicazioni_aziendali.repositories.ComunicazioniAziendaliRepository;
import com.azienda.dipendenti.dto.responses.EntityIdResponse;
import com.azienda.dipendenti.dto.responses.GenericResponse;
import com.azienda.dipendenti.entities.Dipendente;
import com.azienda.dipendenti.exceptions.DipendenteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComunicazioniAziendaleService
{
  @Autowired
  ComunicazioniAziendaliRepository comunicazioniAziendaliRepository;
  @Autowired
  ComunicazioniAziendaliMapper comunicazioniAziendaliMapper;
  @Autowired
  Dipendente dipendente;

  public ComunicazioniAziendaliResponse getComunicazioniAziendaliById(Long id){
    ComunicazioniAziendali comunicazioniAziendali = ComunicazioniAziendaliRepository
            .
            .orElseThrow(() -> new ComunicazioniAziendaliNotFoundException(String.format("La comunicazione aziendale  con id %d non esiste", id)));
    return  ComunicazioniAziendaliMapper.toResponse;
  }

  public List<ComunicazioniAziendali> getAll(){
    return  ComunicazioniAziendaliRepository
            .findAll()
            .stream()
            .map(comunicazioniAziendaliMapper::toResponse)
            .toList();
  }

  public EntityIdResponse createDipendente(DipendenteRequest request){
    var dipartimento = dipartimentoClient.getDipartimentoById(request.dipartimento_id());
    Dipendente dipendente = dipendenteRepository.save(dipendenteMapper.toEntity(request));
    return EntityIdResponse
            .builder()
            .id(dipendente.getId())
            .build();
  }

  public EntityIdResponse updateDipendente(Long id, DipendenteUpdateRequest request){
    var dipartimento = dipartimentoClient.getDipartimentoById(request.dipartimento_id());
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
    if (request.dipartimento_id() != null) dipendente.setDipartimento(request.dipartimento_id());
    dipendenteRepository.save(dipendente);
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
