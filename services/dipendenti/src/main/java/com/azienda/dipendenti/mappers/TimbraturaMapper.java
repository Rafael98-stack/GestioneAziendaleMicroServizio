package com.azienda.dipendenti.mappers;

import com.azienda.dipendenti.dtos.request.TimbraturaRequestRegister;
import com.azienda.dipendenti.dtos.request.TimbraturaRequestUpdate;
import com.azienda.dipendenti.dtos.response.TimbraturaResponse;
import com.azienda.dipendenti.entities.Timbratura;
import com.azienda.dipendenti.repositories.TimbraturaRepository;
import com.azienda.dipendenti.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class TimbraturaMapper {
    @Autowired
    DipendenteService dipendenteService;
    @Autowired
    TimbraturaRepository timbraturaRepository;

    public Timbratura fromTimbraturaRequestRegister(TimbraturaRequestRegister timbraturaRequestRegister) {
        return Timbratura
                .builder()
                .data_corrente(LocalDate.now())
                .build();
    }

    public TimbraturaResponse toResponse(Timbratura timbratura){
        return TimbraturaResponse
                .builder()
                .id(timbratura.getId())
                .dataCorrente(timbratura.getData_corrente())
                .orarioEntrata(timbratura.getOrario_entrata())
                .inizioPranzo(timbratura.getInizio_pranzo())
                .finePranzo(timbratura.getFine_pranzo())
                .uscite(timbratura.getUscita())
                .idDipendente(timbratura.getDipendente().getId())
                .build();
    }

    public Timbratura fromTimbraturaRequestUpdate(TimbraturaRequestUpdate timbraturaRequestUpdate) throws Exception {
        // Aggiorna i campi della timbratura in base all'input
        Timbratura timbratura = timbraturaRepository.findById(timbraturaRequestUpdate.id_timbratura().longValue())
                .orElseThrow(() -> new Exception("Timbratura con ID " + timbraturaRequestUpdate.id_timbratura() + " non trovata"));

        switch (timbraturaRequestUpdate.numero_scelta()) {
            case 1:
                if (timbratura.getOrario_entrata() != null) {
                    throw new Exception("Orario entrata già esistente");
                }
            timbratura.setOrario_entrata(LocalDateTime.now());
                return timbratura;
            case 2:
                if (timbratura.getInizio_pranzo() != null) {
                    throw new Exception("Inizio pranzo già esistente");
                }
                timbratura.setInizio_pranzo(LocalDateTime.now());
                return timbratura;
                case 3:
                if (timbratura.getFine_pranzo() != null) {
                    throw new Exception("Fine pranzo già esistente");
                }
                timbratura.setFine_pranzo(LocalDateTime.now());
                return timbratura;
                case 4:
                if (timbratura.getUscita() != null) {
                throw new Exception("Uscita già esistente");
                }
                timbratura.setUscita(LocalDateTime.now());
                    return timbratura;
                    default:
                System.out.println("Nessuna scelta impostata o Non valida. Sceglielte 1-4");
        }
        return null;
    }
}
