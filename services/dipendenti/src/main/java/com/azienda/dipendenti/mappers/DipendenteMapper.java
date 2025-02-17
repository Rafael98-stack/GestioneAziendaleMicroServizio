package com.azienda.dipendenti.mappers;

import com.azienda.dipendenti.dtos.request.DipendenteRequestRegister;
import com.azienda.dipendenti.dtos.request.DipendenteRequestUpdate;
import com.azienda.dipendenti.dtos.response.DipendenteResponse;
import com.azienda.dipendenti.entities.Dipartimento;
import com.azienda.dipendenti.entities.Dipendente;
import com.azienda.dipendenti.entities.Timbratura;
import com.azienda.dipendenti.repositories.DipartimentoRepository;
import com.azienda.dipendenti.repositories.PosizioneLavorativaRepository;
import com.azienda.dipendenti.repositories.TimbraturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

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

    public DipendenteResponse toResponse(Dipendente dipendente){
        return DipendenteResponse
                .builder()
                .id(dipendente.getId())
                .nome(dipendente.getNome())
                .cognome(dipendente.getCognome())
                .email(dipendente.getEmail())
                .password(dipendente.getPassword())
                .dataNascita(dipendente.getData_nascita())
                .luogoNascita(dipendente.getLuogo_nascita())
                .telefono(dipendente.getTelefono())
                .dipartimento(dipendente.getDipartimento().getId())
                .posizione(dipendente.getPosizioneLavorativa().getId())
                .commenti(Optional.ofNullable(dipendente.getCommenti()).orElse(dipendente.getCommenti()))
                .newses(Optional.ofNullable(dipendente.getNewses()).orElse(Set.of()).stream().toList())
                .comunicazioni(Optional.ofNullable(dipendente.getComunicazioni_aziendali()).orElse(Set.of()).stream().toList())
                .timbrature(dipendente
                        .getTimbratura()
                        .stream()
                        .map(Timbratura::getId)
                        .toList())
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
                .luogo_nascita(dipendenteRequestUpdate.luogo_nascita())
                .telefono(dipendenteRequestUpdate.telefono())
                .immagine_profilo(dipendenteRequestUpdate.immagine_profilo())
                .posizioneLavorativa(posizioneLavorativaRepository
                        .findById(dipendenteRequestUpdate.id_posizione())
                        .orElseThrow(()-> new Exception("Posizione lavorativa con id " + dipendenteRequestUpdate.id_posizione() + " non trovato")))
                .build();
    }
}
