package com.azienda.dipendenti.services;

import com.azienda.dipendenti.dto.responses.DipartimentoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "dipartimenti-service", url = "${application.config.dipartimenti-url}")
public interface DipartimentoClient {
    @GetMapping("/get/{id}")
    DipartimentoResponse getDipartimentoById(@PathVariable Long id);

}
