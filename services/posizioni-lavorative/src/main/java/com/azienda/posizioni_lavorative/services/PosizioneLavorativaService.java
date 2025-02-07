package com.azienda.posizioni_lavorative.services;

import com.azienda.posizioni_lavorative.dtos.PosizioneLavorativaDtos.PosizioneLavorativaRequest;
import com.azienda.posizioni_lavorative.dtos.PosizioneLavorativaDtos.PosizioneLavorativaResponse;
import com.azienda.posizioni_lavorative.dtos.PosizioneLavorativaDtos.PosizioneLavorativaUpdate;
import com.azienda.posizioni_lavorative.entities.PosizioneLavorativa;
import com.azienda.posizioni_lavorative.mappers.PosizioneLavorativaMapper;
import com.azienda.posizioni_lavorative.repositories.PosizioneLavorativaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PosizioneLavorativaService {

    private final PosizioneLavorativaRepository posizioneLavorativaRepository;

    private final PosizioneLavorativaMapper posizioneLavorativaMapper;

    @Autowired
    public PosizioneLavorativaService(PosizioneLavorativaRepository posizioneLavorativaRepository, PosizioneLavorativaMapper posizioneLavorativaMapper) {
        this.posizioneLavorativaRepository = posizioneLavorativaRepository;
        this.posizioneLavorativaMapper = posizioneLavorativaMapper;
    }



    public PosizioneLavorativa getById(Long id) throws Exception {
        return  posizioneLavorativaRepository
                .findById(id)
                .orElseThrow(() -> new Exception("posizione non trovata"));
    }

    public List<PosizioneLavorativa> getAll(){
        return posizioneLavorativaRepository.findAll();
    }

    public PosizioneLavorativaResponse create(PosizioneLavorativaRequest request){
        PosizioneLavorativa posizioneLavorativa = posizioneLavorativaMapper.fromPosizioneLavorativaRequest(request);
        return PosizioneLavorativaResponse
                .builder()
                .id(posizioneLavorativaRepository.save(posizioneLavorativa).getId())
                .build();
    }

    public PosizioneLavorativaResponse updatePosizioneById(Long id, PosizioneLavorativaUpdate posizioneLavorativaUpdate) throws Exception {
        PosizioneLavorativa posizioneLavorativa = posizioneLavorativaRepository.findById(id)
                .orElseThrow(() -> new Exception("Posizione non trovata"));
        if (posizioneLavorativaUpdate.id_dipendente() == null &&
                posizioneLavorativaUpdate.dipartimenti() == null){
            posizioneLavorativa.setNome(posizioneLavorativaUpdate.nome());
            posizioneLavorativa.setDescrizione(posizioneLavorativaUpdate.descrizione());
            return PosizioneLavorativaResponse
                    .builder()
                    .id(posizioneLavorativaRepository.save(posizioneLavorativa).getId())
                    .build();
        }
        return PosizioneLavorativaResponse
                .builder()
                .id(posizioneLavorativaRepository.save(posizioneLavorativa).getId())
                .build();
    }

    public void deleteById(Long id){
        posizioneLavorativaRepository.deleteById(id);
    }
}
