package com.azienda.dipendenti.services;

import com.azienda.dipendenti.dtos.request.PosizioneLavorativaRequestInsert;
import com.azienda.dipendenti.dtos.request.PosizioneLavorativaRequestUpdate;
import com.azienda.dipendenti.dtos.response.PosizioneLavorativaResponse;
import com.azienda.dipendenti.entities.Dipendente;
import com.azienda.dipendenti.entities.PosizioneLavorativa;
import com.azienda.dipendenti.mappers.PosizioneLavorativaMapper;
import com.azienda.dipendenti.repositories.DipartimentoRepository;
import com.azienda.dipendenti.repositories.DipendeteRepository;
import com.azienda.dipendenti.repositories.PosizioneLavorativaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PosizioneLavorativaService {

    private final PosizioneLavorativaRepository posizioneLavorativaRepository;

    private final PosizioneLavorativaMapper posizioneLavorativaMapper;

    private final DipartimentoRepository dipartimentoRepository;

    private final DipendeteRepository dipendeteRepository;

    @Autowired
    public PosizioneLavorativaService(PosizioneLavorativaRepository posizioneLavorativaRepository, PosizioneLavorativaMapper posizioneLavorativaMapper, DipartimentoRepository dipartimentoRepository, DipendeteRepository dipendeteRepository) {
        this.posizioneLavorativaRepository = posizioneLavorativaRepository;
        this.posizioneLavorativaMapper = posizioneLavorativaMapper;
        this.dipartimentoRepository = dipartimentoRepository;
        this.dipendeteRepository = dipendeteRepository;
    }



    public PosizioneLavorativa getById(Long id) throws Exception {
        return  posizioneLavorativaRepository
                .findById(id)
                .orElseThrow(() -> new Exception("posizione non trovata"));
    }

    public List<PosizioneLavorativa> getAll(){
        return posizioneLavorativaRepository.findAll();
    }

    public PosizioneLavorativaResponse create(PosizioneLavorativaRequestInsert request){
        PosizioneLavorativa posizioneLavorativa = posizioneLavorativaMapper.fromPosizioneLavorativaRequest(request);
        return PosizioneLavorativaResponse
                .builder()
                .id(posizioneLavorativaRepository.save(posizioneLavorativa).getId())
                .build();
    }

    public PosizioneLavorativaResponse updatePosizioneById(Long id, PosizioneLavorativaRequestUpdate posizioneLavorativaUpdate) throws Exception {
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
        Dipendente dipendente = dipendeteRepository.findById(posizioneLavorativaUpdate.id_dipendente())
                .orElseThrow(() ->  new Exception("Dipendente non trovato"));

        dipendente.setPosizioneLavorativa(posizioneLavorativa);
        dipendeteRepository.save(dipendente);

        posizioneLavorativa.setNome(posizioneLavorativaUpdate.nome());
        posizioneLavorativa.setDescrizione(posizioneLavorativaUpdate.descrizione());
        posizioneLavorativa.setDipartimenti(posizioneLavorativaUpdate.dipartimenti().stream().map(id_dip -> {
            try {
                return dipartimentoRepository.findById(id_dip)
                        .orElseThrow(() ->  new Exception("Dipartimento non trovato"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toSet()));

        return PosizioneLavorativaResponse
                .builder()
                .id(posizioneLavorativaRepository.save(posizioneLavorativa).getId())
                .build();
    }

    public void deleteById(Long id){
        posizioneLavorativaRepository.deleteById(id);
    }
}
