package com.azienda.comunicazioni_aziendali.controllers;

import com.azienda.comunicazioni_aziendali.dto.request.ComunicazioniAziendaliRequest;
import com.azienda.comunicazioni_aziendali.dto.request.ComunicazioniAziendaliUpdateRequest;
import com.azienda.comunicazioni_aziendali.dto.response.ComunicazioniAziendaliResponse;
import com.azienda.comunicazioni_aziendali.services.ComunicazioniAziendaleService;
import com.azienda.dipendenti.dto.responses.EntityIdResponse;
import com.azienda.dipendenti.dto.responses.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comunicaziniaziendali")
public class ComunicazioniAziendaliController
{
    @Autowired
    ComunicazioniAziendaleService comunicazioniAziendaleService;

    @GetMapping("/get/{id}")
    public ResponseEntity<ComunicazioniAziendaliResponse> getComunicazioniAziendaliById(@PathVariable Long id){
        return new ResponseEntity<>(comunicazioniAziendaleService.getComunicazioniAziendaliById(id), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<ComunicazioniAziendaliResponse>> getAllComunicazioniAziendali(){
        return new ResponseEntity<>(comunicazioniAziendaleService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<EntityIdResponse> createComunicazioniAziendali(@RequestBody ComunicazioniAziendaliRequest request){
        return new ResponseEntity<>(comunicazioniAziendaleService.createComunicazione(request), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EntityIdResponse> updateComunicazioniAziendali(@PathVariable Long id, @RequestBody ComunicazioniAziendaliUpdateRequest request){
        return new ResponseEntity<>(comunicazioniAziendaleService.updateComunicazione(id, request), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse> deleteComunicazioniAziendaliById(@PathVariable Long id){
        return new ResponseEntity<>(comunicazioniAziendaleService.deleteComunicazioneById(id), HttpStatus.OK);
    }
}
