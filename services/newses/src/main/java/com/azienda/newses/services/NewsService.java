package com.azienda.newses.services;

import com.azienda.newses.DipedenteClient;
import com.azienda.newses.dto.request.NewsRequest;
import com.azienda.newses.dto.request.NewsUpdateRequest;
import com.azienda.newses.dto.response.NewsResponse;
import com.azienda.newses.entities.News;
import com.azienda.newses.exceptions.NewsNotFoundException;
import com.azienda.newses.mappers.NewsMapper;
import com.azienda.newses.repositories.NewsRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService
{
   @Autowired
   NewsRepository newsRepository;
   @Autowired
   NewsMapper newsMapper;
   @Autowired
   PosizioneLavorativaClient posizioneLavorativaClient;
   @Autowired
   DipedenteClient dipedenteClient;


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

    public NewsResponse createNews(@Valid NewsRequest request) throws Exception {
        dipedenteClient.getDipendenteById(request.id_dipendente());

        if (posizioneLavorativaClient.getNomePosizioneById(request.id_dipendente()).equalsIgnoreCase("publisher"))
        {
            News news = newsMapper.fromNewsRequest(request);
            news.setLikes(0l);
            return NewsResponse
                    .builder()
                    .id(newsRepository.save(news).getId())
                    .build();
        } else
        {
            throw new Exception("Non puoi creare una News");
        }
    }

    public NewsResponse updateNews(Long id, @Valid NewsUpdateRequest request) throws Exception {
        dipedenteClient.getDipendenteById(request.id_dipendente());

        if (posizioneLavorativaClient.getNomePosizioneById(request.id_dipendente()).equalsIgnoreCase("publisher")){
            News news = newsRepository.findById(id)
                    .orElseThrow(() -> new NewsNotFoundException("news non esistente"));
            news.setTitolo(request.titolo());
            news.setContenuto(request.contenuto());
            news.setImmagine(request.immagine());
            return NewsResponse
                    .builder()
                    .id( newsRepository.save(news).getId())
                    .build();
        } else
        {
            throw  new Exception("Non puoi aggiornare una News");
        }
    }


    public void removeNews(Long id)
    {
        newsRepository.deleteById(id);
    }

    public void likeNews(Long id)
    {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new NewsNotFoundException("news non esistente"));
        news.setLikes(news.getLikes() + 1);
        newsRepository.save(news);
    }
}
