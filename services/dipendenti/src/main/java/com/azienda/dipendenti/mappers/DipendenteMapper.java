package com.azienda.dipendenti.mappers;

import com.azienda.dipendenti.dtos.request.DipendenteRequestRegister;
import com.azienda.dipendenti.dtos.request.DipendenteRequestUpdate;
import com.azienda.dipendenti.entities.Dipartimento;
import com.azienda.dipendenti.entities.Dipendente;
import com.azienda.dipendenti.repositories.DipartimentoRepository;
import com.azienda.dipendenti.repositories.PosizioneLavorativaRepository;
import com.azienda.dipendenti.repositories.TimbraturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DipendenteMapper {

    @Autowired
    DipartimentoRepository dipartimentoRepository;

    @Autowired
    PosizioneLavorativaRepository posizioneLavorativaRepository;

    @Autowired
    TimbraturaRepository timbraturaRepository;

    public Dipendente fromDipendenteRequestRegister(DipendenteRequestRegister dipendenteRequestRegister){
        Dipartimento dipartimento = null;
        if (dipendenteRequestRegister.id_dipartimento() != null) {
            dipartimento = dipartimentoRepository.findById(dipendenteRequestRegister.id_dipartimento()).orElse(null);
        }

        return Dipendente.builder()
                .email(dipendenteRequestRegister.email())
                .password(dipendenteRequestRegister.password())
                .cognome(dipendenteRequestRegister.cognome())
                .data_nascita(dipendenteRequestRegister.data_nascita())
                .nome(dipendenteRequestRegister.nome())
                .luogo_nascita(dipendenteRequestRegister.luogo_nascita())
                .telefono(dipendenteRequestRegister.telefono())
                .immagine_profilo(dipendenteRequestRegister.immagine_profilo())
                .build();
    }


    public Dipendente fromDipendenteRequestUpdate(DipendenteRequestUpdate dipendenteRequestUpdate) throws Exception {
        return Dipendente
                .builder()
                .email(dipendenteRequestUpdate.email())
                .password(dipendenteRequestUpdate.password())
                .cognome(dipendenteRequestUpdate.cognome())
                .data_nascita(dipendenteRequestUpdate.data_nascita())
                .nome(dipendenteRequestUpdate.nome())
                .dipartimento(dipartimentoRepository.findById(dipendenteRequestUpdate.id_dipartimento())
                        .orElseThrow(()-> new Exception("Dipartimento con id " + dipendenteRequestUpdate.id_dipartimento() + " non trovato")))
                .timbratura(timbraturaRepository.findById(dipendenteRequestUpdate.id_timbratura())
                        .orElseThrow(()-> new Exception("Timbratura con id " + dipendenteRequestUpdate.id_timbratura() + " non trovato")))
                .luogo_nascita(dipendenteRequestUpdate.luogo_nascita())
                .telefono(dipendenteRequestUpdate.telefono())
                .immagine_profilo(dipendenteRequestUpdate.immagine_profilo())
                .posizioneLavorativa(posizioneLavorativaRepository
                        .findById(dipendenteRequestUpdate.id_posizione())
                        .orElseThrow(()-> new Exception("Posizione lavorativa con id " + dipendenteRequestUpdate.id_posizione() + " non trovato")))
                .build();
    }
}
