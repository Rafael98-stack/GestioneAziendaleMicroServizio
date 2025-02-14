package com.azienda.dipendenti.services;

import com.azienda.dipendenti.dtos.request.TimbraturaRequestRegister;
import com.azienda.dipendenti.dtos.request.TimbraturaRequestUpdate;
import com.azienda.dipendenti.dtos.response.TimbraturaResponse;
import com.azienda.dipendenti.entities.Dipendente;
import com.azienda.dipendenti.entities.Timbratura;
import com.azienda.dipendenti.mappers.TimbraturaMapper;
import com.azienda.dipendenti.repositories.DipendeteRepository;
import com.azienda.dipendenti.repositories.TimbraturaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimbraturaService
{
    @Autowired
    private TimbraturaRepository timbraturaRepository;
    @Autowired
    private TimbraturaMapper timbraturaMapper;
    @Autowired
    private DipendeteRepository dipendeteRepository;
    @Autowired
    private DipendenteService dipendenteService;

    public TimbraturaResponse registerTimbratura(TimbraturaRequestRegister timbraturaRequestRegister)
    {
        Timbratura timbratura = timbraturaMapper.fromTimbraturaRequestRegister(timbraturaRequestRegister);
        timbratura.setDipendente(dipendenteService.getDipendenteById(timbraturaRequestRegister.id_dipendente()));

        return TimbraturaResponse
                .builder()
                .id(timbraturaRepository.save(timbratura).getId())
                .build();
    }

    public Timbratura getTimbraturaById(Long idTimbratura)
    {
        return timbraturaRepository.
                findById(idTimbratura)
                .orElseThrow(() -> new EntityNotFoundException("Timbratura con ID " + idTimbratura + " non trovata"));
    }

    public List<Timbratura> getAllTimbratura()
    {
        return timbraturaRepository.findAll();
    }

public TimbraturaResponse updateTimbraturaById(TimbraturaRequestUpdate timbraturaRequestUpdate) throws Exception {
        // Recupera la timbratura esistente o lancia un'eccezione se non trovata
    Timbratura timbratura = timbraturaMapper.fromTimbraturaRequestUpdate(timbraturaRequestUpdate);
    Dipendente dipendente = dipendeteRepository.findById(timbraturaRequestUpdate.id_dipendente())
            .orElseThrow(() -> new Exception("Dipendente con ID " + timbraturaRequestUpdate.id_dipendente() + " non trovata"));
    timbratura.setDipendente(dipendente);
    // Salva la timbratura aggiornata
        return TimbraturaResponse
                .builder()
                .id(timbraturaRepository.save(timbratura).getId())
                .build();
    }


    public void removeTimbraturaById(Long idTimbratura){timbraturaRepository.deleteById(idTimbratura);}

    }

