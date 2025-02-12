package com.azienda.comunicazioni_aziendali.services;

import com.azienda.dipendenti.dto.responses.DipendenteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "dipedenti-service", url = "${application.config.dipendenti-url}")
public interface DipedenteClient {

    @GetMapping("/get/{id}")
    DipendenteResponse getDipendenteById(@PathVariable Long id);

}
