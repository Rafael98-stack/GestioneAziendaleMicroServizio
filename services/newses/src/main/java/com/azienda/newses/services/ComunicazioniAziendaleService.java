package com.azienda.newses.services;

import com.azienda.comunicazioni_aziendali.dto.request.ComunicazioniAziendaliRequest;
import com.azienda.comunicazioni_aziendali.dto.request.ComunicazioniAziendaliUpdateRequest;
import com.azienda.comunicazioni_aziendali.dto.response.ComunicazioniAziendaliResponse;
import com.azienda.comunicazioni_aziendali.entities.ComunicazioniAziendali;
import com.azienda.comunicazioni_aziendali.exceptions.ComunicazioniAziendaliNotFoundException;
import com.azienda.comunicazioni_aziendali.kafka.ComunicazioneAziendaleMessage;
import com.azienda.comunicazioni_aziendali.kafka.ComunicazioniProducer;
import com.azienda.comunicazioni_aziendali.mapper.ComunicazioniAziendaliMapper;
import com.azienda.comunicazioni_aziendali.repositories.ComunicazioniAziendaliRepository;
import com.azienda.comunicazioni_aziendali.services.DipedenteClient;
import com.azienda.dipendenti.dto.responses.EntityIdResponse;
import com.azienda.dipendenti.dto.responses.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ComunicazioniAziendaleService
{
  @Autowired
  ComunicazioniAziendaliRepository comunicazioniAziendaliRepository;
  @Autowired
  ComunicazioniAziendaliMapper comunicazioniAziendaliMapper;
  @Autowired
  DipedenteClient dipedenteClient;
  @Autowired
  ComunicazioniProducer comunicazioniProducer;

  public ComunicazioniAziendaliResponse getComunicazioniAziendaliById(Long id){
    ComunicazioniAziendali comunicazioniAziendali = comunicazioniAziendaliRepository.findById(id)
            .orElseThrow(() -> new ComunicazioniAziendaliNotFoundException(String.format("La comunicazione aziendale  con id %d non esiste", id)));
    return  comunicazioniAziendaliMapper.toResponse(comunicazioniAziendali);
  }

  public List<ComunicazioniAziendaliResponse> getAll(){
    return  comunicazioniAziendaliRepository
            .findAll()
            .stream()
            .map(comunicazioniAziendaliMapper::toResponse)
            .toList();
  }

  public EntityIdResponse createComunicazione(ComunicazioniAziendaliRequest request){
    var dipendente = dipedenteClient.getDipendenteById(request.id_dipendente());
    ComunicazioniAziendali comunicazioniAziendali = comunicazioniAziendaliRepository.save(comunicazioniAziendaliMapper.toEntity(request));
    comunicazioniProducer.sendConfermaComunicazione(ComunicazioneAziendaleMessage
            .builder()
            .id(comunicazioniAziendali.getId())
            .contenuto(comunicazioniAziendali.getContenuto())
            .titolo(comunicazioniAziendali.getTitolo())
            .id_dipendente(comunicazioniAziendali.getDipendente())
            .timestamp(LocalDateTime.now())
            .build());
    return EntityIdResponse
            .builder()
            .id(comunicazioniAziendali.getId())
            .build();
  }

  public EntityIdResponse updateComunicazione(Long id, ComunicazioniAziendaliUpdateRequest request){
    var dipendente = dipedenteClient.getDipendenteById(request.id_dipendente());
    ComunicazioniAziendali comunicazioniAziendali = comunicazioniAziendaliRepository
            .findById(id)
            .orElseThrow(() -> new ComunicazioniAziendaliNotFoundException(String.format("Il comunicazioniAziendali con id %d non esiste", id)));
    if (request.id_dipendente() != null) comunicazioniAziendali.setDipendente(request.id_dipendente());
    if (request.titolo() != null) comunicazioniAziendali.setTitolo(request.titolo());
    if (request.contenuto() != null) comunicazioniAziendali.setContenuto(request.contenuto());
    comunicazioniAziendaliRepository.save(comunicazioniAziendali);
    return EntityIdResponse
            .builder()
            .id(comunicazioniAziendali.getId())
            .build();
  }

  public GenericResponse deleteComunicazioneById(Long id){
    comunicazioniAziendaliRepository.deleteById(id);
    return GenericResponse
            .builder()
            .message(String.format("La Comunicazione con id %d è stato eliminato", id))
            .build();
  }


}
