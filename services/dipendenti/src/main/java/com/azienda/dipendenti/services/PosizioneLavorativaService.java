package com.azienda.dipendenti.services;

import com.azienda.dipendenti.dtos.request.PosizioneLavorativaRequestInsert;
import com.azienda.dipendenti.dtos.request.PosizioneLavorativaRequestUpdate;
import com.azienda.dipendenti.dtos.response.EntityIdResponse;
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

@Service
public class PosizioneLavorativaService {

    private final PosizioneLavorativaRepository posizioneLavorativaRepository;

    private final PosizioneLavorativaMapper posizioneLavorativaMapper;

    private final DipartimentoRepository dipartimentoRepository;

    private final DipartimentoService dipartimentoService;

    private final DipendeteRepository dipendeteRepository;

    @Autowired
    public PosizioneLavorativaService(PosizioneLavorativaRepository posizioneLavorativaRepository, PosizioneLavorativaMapper posizioneLavorativaMapper, DipartimentoRepository dipartimentoRepository, DipartimentoService dipartimentoService, DipendeteRepository dipendeteRepository) {
        this.posizioneLavorativaRepository = posizioneLavorativaRepository;
        this.posizioneLavorativaMapper = posizioneLavorativaMapper;
        this.dipartimentoRepository = dipartimentoRepository;
        this.dipartimentoService = dipartimentoService;
        this.dipendeteRepository = dipendeteRepository;
    }



    public PosizioneLavorativa getById(Long id) throws Exception {
        return  posizioneLavorativaRepository
                .findById(id)
                .orElseThrow(() -> new Exception("posizione non trovata"));
    }

    public PosizioneLavorativaResponse getByIdToResponse(Long id) throws Exception {
        return posizioneLavorativaMapper
                .toResponse(posizioneLavorativaRepository
                        .findById(id)
                        .orElseThrow(() -> new Exception("posizione non trovata")));
    }

    public List<PosizioneLavorativaResponse> getAll(){
        return posizioneLavorativaRepository.findAll().stream().map(posizioneLavorativaMapper::toResponse).toList();
    }

    public EntityIdResponse create(PosizioneLavorativaRequestInsert request) throws Exception {
        PosizioneLavorativa posizioneLavorativa = posizioneLavorativaMapper.fromPosizioneLavorativaRequest(request);
        return EntityIdResponse
                .builder()
                .id(posizioneLavorativaRepository.save(posizioneLavorativa).getId())
                .build();
    }

    public PosizioneLavorativaResponse updatePosizioneById(Long id, PosizioneLavorativaRequestUpdate posizioneLavorativaUpdate) throws Exception {
        PosizioneLavorativa posizioneLavorativa = posizioneLavorativaRepository.findById(id)
                .orElseThrow(() -> new Exception("Posizione non trovata"));
        if (posizioneLavorativaUpdate.id_dipendente() == null &&
                posizioneLavorativaUpdate.dipartimento() == null){
            posizioneLavorativa.setNome(posizioneLavorativaUpdate.nome());
            posizioneLavorativa.setDescrizione(posizioneLavorativaUpdate.descrizione());
            posizioneLavorativaRepository.save(posizioneLavorativa);
            return posizioneLavorativaMapper.toResponse(posizioneLavorativa);
        }
        Dipendente dipendente = dipendeteRepository.findById(posizioneLavorativaUpdate.id_dipendente())
                .orElseThrow(() ->  new Exception("Dipendente non trovato"));

        dipendente.setPosizioneLavorativa(posizioneLavorativa);
        dipendeteRepository.save(dipendente);

        posizioneLavorativa.setNome(posizioneLavorativaUpdate.nome());
        posizioneLavorativa.setDescrizione(posizioneLavorativaUpdate.descrizione());
        posizioneLavorativa.setDipartimento(dipartimentoService.getDipartimentoById(posizioneLavorativaUpdate.dipartimento()));
        posizioneLavorativaRepository.save(posizioneLavorativa);
        return posizioneLavorativaMapper.toResponse(posizioneLavorativa);
    }

    public void deleteById(Long id){
        posizioneLavorativaRepository.deleteById(id);
    }

    public String nomePosizioneDipendente(Long id_dip){
        return dipendeteRepository.findById(id_dip).orElseThrow().getPosizioneLavorativa().getNome();
    }
}
