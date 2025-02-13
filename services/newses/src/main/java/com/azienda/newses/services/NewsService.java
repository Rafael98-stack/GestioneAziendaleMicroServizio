package com.azienda.newses.services;

import com.azienda.dipendenti.entities.Dipendente;
import com.azienda.dipendenti.repositories.DipendeteRepository;
import com.azienda.newses.dto.request.NewsRequest;
import com.azienda.newses.dto.request.NewsUpdateRequest;
import com.azienda.newses.dto.response.NewsResponse;
import com.azienda.newses.entities.News;
import com.azienda.newses.exceptions.NewsNotFoundException;
import com.azienda.newses.mappers.NewsMapper;
import com.azienda.newses.repositories.NewsRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NewsService
{
   @Autowired
   NewsRepository newsRepository;
   @Autowired
   NewsMapper newsMapper;
   @Autowired
   Dipendente dipendente;
   @Autowired
    DipendeteRepository dipendeteRepository;
   @Autowired
   NewsUpdateRequest newsUpdateRequest;
   @Autowired
   PosizioneLavorativaClient posizioneLavorativaClient;


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
        Dipendente dipendente = dipendeteRepository.findById(request.id_dipendente())
                .orElseThrow(() -> new Exception("Dipendente non esiste"));

        var posizioneLavorativa = posizioneLavorativaClient.getPosizioneLavorativaById(dipendente.getPosizioneLavorativa().getId());


        if (posizioneLavorativaClient.getNomePosizioneById(posizioneLavorativa.id()).equalsIgnoreCase("publisher"))
        {
            News news = newsMapper.fromNewsRequest(request);
            news.setLike(0l);
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
        Dipendente dipendente = dipendeteRepository.findById(newsUpdateRequest.id_dipendente())
                .orElseThrow(() -> new Exception("Dipendente non esiste"));

        var posizioneLavorativa = posizioneLavorativaClient.getPosizioneLavorativaById(dipendente.getPosizioneLavorativa().getId());

        if (posizioneLavorativaClient.getNomePosizioneById(posizioneLavorativa.id()).equalsIgnoreCase("publisher")){
            News news = newsRepository.findById(id)
                    .orElseThrow(() -> new NewsNotFoundException("news non esistente"));
            news.setTitolo(newsUpdateRequest.titolo());
            news.setContenuto(newsUpdateRequest.contenuto());
            news.setImmagine(newsUpdateRequest.immagine());
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
        news.setLike(news.getLike() + 1);
        newsRepository.save(news);
    }
}
