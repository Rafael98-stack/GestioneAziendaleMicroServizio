package com.azienda.newses.services;

import com.azienda.dipendenti.dtos.response.PosizioneLavorativaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "newses-service", url = "${application.config.posizioni-lavorative-url}")
public interface PosizioneLavorativaClient {

    @GetMapping("/get/{id}")
    PosizioneLavorativaResponse getPosizioneLavorativaById(@PathVariable Long id);

    @GetMapping("/type/{id}")
    String getNomePosizioneById(@PathVariable Long id);


}
