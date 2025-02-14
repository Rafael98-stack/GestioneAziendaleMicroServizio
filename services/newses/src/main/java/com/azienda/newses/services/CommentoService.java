package com.azienda.newses.services;


import com.azienda.newses.DipedenteClient;
import com.azienda.newses.dto.request.CommentoRequestInsert;
import com.azienda.newses.dto.request.CommentoRequestUpdate;
import com.azienda.newses.dto.response.CommentoResponse;
import com.azienda.newses.entities.Commento;
import com.azienda.newses.mappers.CommentoMapper;
import com.azienda.newses.repositories.CommentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentoService {


    private final CommentoRepository commentoRepository;


    private final CommentoMapper commentoMapper;


    private final DipedenteClient dipedenteClient;

    @Autowired
    public CommentoService(CommentoRepository commentoRepository, CommentoMapper commentoMapper, DipedenteClient dipedenteClient) {
        this.commentoRepository = commentoRepository;
        this.commentoMapper = commentoMapper;
        this.dipedenteClient = dipedenteClient;
    }

    public CommentoResponse insertCommento(CommentoRequestInsert commentoRequestInsert) throws Exception {
        Commento commento = commentoMapper.fromCommentoRequestInsert(commentoRequestInsert);
        return CommentoResponse
                .builder()
                .id(commentoRepository.save(commento).getId())
                .build();
    }

    public CommentoResponse getCommentoById(Long id_commento) throws Exception {
        return CommentoResponse
                .builder()
                .id(commentoRepository.findById(id_commento).orElseThrow(() -> new Exception("Commento con id " + id_commento + " non trovato")).getId())
                .build();
    }

    public List<CommentoResponse> gtAllCommenti() {
        return commentoRepository
                .findAll()
                .stream()
                .map(commentoMapper::toResponse)
                .toList();
    }

    public CommentoResponse updateCommentoById(Long id_commento, CommentoRequestUpdate commentoRequestUpdate) throws Exception {
        Commento commento = commentoRepository.findById(id_commento)
                .orElseThrow(() -> new Exception("Commento con id " + id_commento + " non trovato"));
        commento.setContenuto(commentoRequestUpdate.contenuto());
        commento.setDipendente(dipedenteClient.getDipendenteById(commentoRequestUpdate.id_dipendente()).id());
        commento.setNews(commentoRepository.findById(commentoRequestUpdate.id_newse()).get().getNews());

        return CommentoResponse
                .builder()
                .id(commentoRepository.save(commento).getId())
                .build();
    }

    public void removeCommentoById(Long id_commento) {
        commentoRepository.deleteById(id_commento);
    }
}
