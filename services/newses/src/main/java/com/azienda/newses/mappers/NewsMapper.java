package com.azienda.newses.mappers;

import com.azienda.newses.dto.response.NewsResponse;
import com.azienda.newses.entities.News;
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
}
