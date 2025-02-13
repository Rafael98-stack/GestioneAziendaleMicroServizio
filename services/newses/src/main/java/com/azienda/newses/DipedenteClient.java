package com.azienda.newses;

import com.azienda.dipendenti.dtos.response.DipendenteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "dipedenti-service", url = "${application.config.dipendenti-url}")
public interface DipedenteClient {

    @GetMapping("/get/{id}")
    DipendenteResponse getDipendenteById(@PathVariable Long id);

}
