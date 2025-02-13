package com.azienda.newses.services;

import com.azienda.newses.dto.request.NewsRequest;
import com.azienda.newses.dto.request.NewsUpdateRequest;
import com.azienda.newses.dto.response.NewsResponse;
import com.azienda.newses.entities.News;
import com.azienda.newses.exceptions.NewsNotFoundException;
import com.azienda.newses.mappers.NewsMapper;
import com.azienda.newses.repositories.NewsRepository;
import com.netflix.discovery.converters.Auto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import com.azienda.newses.dto.response.EntityIdResponse;
import java.util.List;

public class NewsService
{
   @Autowired
   NewsRepository newsRepository;
   @Autowired
   NewsMapper newsMapper;


    public NewsResponse getNewsById(Long id)
    {
        News news = newsRepository.findById(id).orElseThrow(() -> new NewsNotFoundException(String.format("La news  con id %d non esiste", id)));
        return  newsMapper.toResponse(news);
    }

    public List<NewsResponse> getAllNews()
    {
      return newsRepository
              .findAll()
              .stream()
              .map(newsMapper::toResponse)
              .toList();
    }

    public NewsResponse createNews(@Valid NewsRequest request)
    {
                 
    }

    public NewsResponse updateNews(Long id, @Valid NewsUpdateRequest request) {
    }

    public void removeNews(Long id) {
    }

    public void likeNews(Long id) {
    }
}
