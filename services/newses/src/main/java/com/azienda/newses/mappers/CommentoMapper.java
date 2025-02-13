package com.azienda.newses.mappers;

import com.azienda.newses.DipedenteClient;
import com.azienda.newses.dto.request.CommentoRequestInsert;
import com.azienda.newses.dto.response.CommentoResponse;
import com.azienda.newses.entities.Commento;
import com.azienda.newses.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentoMapper {

    private final DipedenteClient dipedenteClient;

    private final NewsRepository newsRepository;

    @Autowired
    public CommentoMapper(DipedenteClient dipedenteClient, NewsRepository newsRepository) {
        this.dipedenteClient = dipedenteClient;
        this.newsRepository = newsRepository;
    }

    public Commento fromCommentoRequestInsert(CommentoRequestInsert commentoRequestInsert) throws Exception {
        return Commento
                .builder()
                .news(newsRepository.findById(commentoRequestInsert.id_newse()).orElseThrow(() -> new Exception("News non trovato.")))
                .dipendente(commentoRequestInsert.id_dipendente())
                .contenuto(commentoRequestInsert.contenuto())
                .build();
    }

    public CommentoResponse toResponse(Commento commento) {
        return CommentoResponse
                .builder()
                .id(commento.getId())
                .build();
    }
}
