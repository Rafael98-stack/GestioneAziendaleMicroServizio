package com.azienda.dipendenti.controllers;

import com.azienda.dipendenti.dto.request.DipendenteRequest;
import com.azienda.dipendenti.dto.request.DipendenteUpdateRequest;
import com.azienda.dipendenti.dto.responses.DipendenteResponse;
import com.azienda.dipendenti.dto.responses.EntityIdResponse;
import com.azienda.dipendenti.dto.responses.GenericResponse;
import com.azienda.dipendenti.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dipendenti")
public class DipendenteController {
    @Autowired
    DipendenteService dipendenteService;

    @GetMapping("/get/{id}")
    public ResponseEntity<DipendenteResponse> getDipendenteById(@PathVariable Long id){
        return new ResponseEntity<>(dipendenteService.getDipendenteById(id), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<DipendenteResponse>> getAllDipendenti(){
        return new ResponseEntity<>(dipendenteService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<EntityIdResponse> createDipendente(@RequestBody DipendenteRequest request){
        return new ResponseEntity<>(dipendenteService.createDipendente(request), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EntityIdResponse> updateDipendente(@PathVariable Long id,@RequestBody DipendenteUpdateRequest request){
        return new ResponseEntity<>(dipendenteService.updateDipendente(id, request), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse> deleteDipendenteById(@PathVariable Long id){
        return new ResponseEntity<>(dipendenteService.deleteDipendenteById(id), HttpStatus.OK);
    }
}
