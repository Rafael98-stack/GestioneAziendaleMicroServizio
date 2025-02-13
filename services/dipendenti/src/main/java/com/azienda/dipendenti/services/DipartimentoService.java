package com.azienda.dipendenti.services;

import com.azienda.dipendenti.dtos.request.DipartimentoRequestInsert;
import com.azienda.dipendenti.dtos.request.DipartimentoRequestUpdate;
import com.azienda.dipendenti.dtos.response.DipartimentoResponse;
import com.azienda.dipendenti.entities.Dipartimento;
import com.azienda.dipendenti.entities.Dipendente;
import com.azienda.dipendenti.mappers.DipartimentoMapper;
import com.azienda.dipendenti.repositories.DipartimentoRepository;
import com.azienda.dipendenti.repositories.DipendeteRepository;
import com.azienda.dipendenti.repositories.PosizioneLavorativaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DipartimentoService {


    private final DipartimentoRepository dipartimentoRepository;

    private final DipartimentoMapper dipartimentoMapper;

    private final PosizioneLavorativaRepository posizioneLavorativaRepository;

    private final DipendeteRepository dipendeteRepository;

    @Autowired
    public DipartimentoService(DipartimentoRepository dipartimentoRepository, DipartimentoMapper dipartimentoMapper, PosizioneLavorativaRepository posizioneLavorativaRepository, DipendeteRepository dipendeteRepository) {
        this.dipartimentoRepository = dipartimentoRepository;
        this.dipartimentoMapper = dipartimentoMapper;
        this.posizioneLavorativaRepository = posizioneLavorativaRepository;
        this.dipendeteRepository = dipendeteRepository;
    }

    public DipartimentoResponse insertDipartimento(DipartimentoRequestInsert dipartimentoRequestInsert){
    Dipartimento dipartimento = dipartimentoMapper.fromDipartimentoRequestInsert(dipartimentoRequestInsert);

        return DipartimentoResponse
                .builder()
                .id(dipartimentoRepository.save(dipartimento).getId())
                .build();
    }

    public Dipartimento getDipartimentoById(Long id_dipartimento) throws Exception {
        return dipartimentoRepository.findById(id_dipartimento)
                .orElseThrow(() -> new Exception("Dipartimento con id " + id_dipartimento + " non trovato"));
    }

    public DipartimentoResponse updateDipartimentoById(Long id_dipartimento, DipartimentoRequestUpdate dipartimentoRequestUpdate) throws Exception {
        Dipartimento dipartimento = dipartimentoRepository.findById(id_dipartimento)
                .orElseThrow(() -> new Exception("Dipartimento con id " + id_dipartimento + " non trovato"));
        if (dipartimentoRequestUpdate.id_dipedente() == null &&
        dipartimentoRequestUpdate.id_posizione_lavorativa() == null
        ){
            dipartimento.setNome(dipartimentoRequestUpdate.nome());
            dipartimento.setDescrizione(dipartimentoRequestUpdate.descrizione());
            return DipartimentoResponse
                    .builder()
                    .id(dipartimentoRepository.save(dipartimento).getId())
                    .build();
        }
        dipartimento.setNome(dipartimentoRequestUpdate.nome());
        dipartimento.setDescrizione(dipartimentoRequestUpdate.descrizione());
        dipartimento.setPosizioniLavorative(dipartimentoRequestUpdate.id_posizione_lavorativa().stream().map(id -> {
            try {
                return posizioneLavorativaRepository.findById(id)
                           .orElseThrow(() -> new Exception("Posizione con id " + dipartimentoRequestUpdate.id_posizione_lavorativa() + " non trovato"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toSet()));

        Dipendente dipendente = dipendeteRepository.findById(dipartimentoRequestUpdate.id_dipedente())
                .orElseThrow(() -> new Exception("Dipendente con id " + dipartimentoRequestUpdate.id_dipedente() + " non trovato"));

        dipendente.setDipartimento(dipartimento);
        dipendeteRepository.save(dipendente);

        return DipartimentoResponse
                .builder()
                .id(dipartimentoRepository.save(dipartimento).getId())
                .build();
    }

    public List<Dipartimento> getAllDipartimenti(){
        return dipartimentoRepository.findAll();
    }

    public void removeDipartimentoById(Long id_dipartimento){
        dipartimentoRepository.deleteById(id_dipartimento);
    }
}
