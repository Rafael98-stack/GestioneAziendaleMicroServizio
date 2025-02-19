package com.azienda.newses.controllers;

import com.azienda.newses.ComunicazioniAziendaliResponse;
import com.azienda.newses.dto.response.EntityIdResponse;
import com.azienda.newses.dto.response.GenericResponse;
import com.azienda.newses.request.ComunicazioniAziendaliRequest;
import com.azienda.newses.request.ComunicazioniAziendaliUpdateRequest;
import com.azienda.newses.services.ComunicazioniAziendaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/v1/comunicaziniaziendali")
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
