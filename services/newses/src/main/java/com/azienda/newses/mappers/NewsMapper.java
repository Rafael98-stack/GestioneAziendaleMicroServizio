package com.azienda.newses.mappers;

import com.azienda.newses.dto.request.NewsRequest;
import com.azienda.newses.dto.response.NewsResponse;
import com.azienda.newses.entities.News;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class NewsMapper
{
    public NewsResponse toResponse(News news)
    {
        return NewsResponse
                .builder()
                .id(news.getId())
                .titolo(news.getTitolo())
                .contenuto(news.getContenuto())
                .imagine(news.getImmagine())
                .build();
    }

    public News fromNewsRequest(@Valid NewsRequest request)
    {
        News news = new News(request.immagine(), request.titolo(), request.contenuto(), request.id_dipendente(),request.like());
        return news;
    }
}
