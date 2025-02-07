package com.azienda.dipendenti.services;

import com.azienda.dipendenti.dto.responses.PosizioneLavorativaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "posizioni-lavorative-service", url = "${application.config.posizioni-lavorative-url}")
public interface PosizioneClient {

    @GetMapping("/get/{id}")
    PosizioneLavorativaResponse getPosizioneById(@PathVariable Long id);

}
