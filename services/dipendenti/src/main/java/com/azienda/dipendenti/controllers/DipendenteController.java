package com.azienda.dipendenti.controllers;

import com.azienda.dipendenti.dtos.request.DipendenteRequestRegister;
import com.azienda.dipendenti.dtos.request.DipendenteRequestUpdate;
import com.azienda.dipendenti.dtos.response.DipendenteResponse;
import com.azienda.dipendenti.dtos.response.EntityIdResponse;
import com.azienda.dipendenti.services.DipendenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/v1/Dipendente")
@CrossOrigin(origins = "http://localhost:8072")
public class DipendenteController {

    @Autowired
    DipendenteService dipendenteService;

    @GetMapping("/get/{id}")
    public ResponseEntity<DipendenteResponse> getById(@PathVariable Long id){
        return new ResponseEntity<>(dipendenteService.getDipendenteByIdToResponse(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DipendenteResponse>> getAll(){
        return  new ResponseEntity<>(dipendenteService.getAllDipendenti(),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<EntityIdResponse> create(@RequestBody @Valid DipendenteRequestRegister request){
        return new ResponseEntity<>(dipendenteService.registerDipendente(request), HttpStatus.CREATED);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<DipendenteResponse> update(@PathVariable Long id, @RequestBody @Valid DipendenteRequestUpdate request) throws Exception {
        return new ResponseEntity<>(dipendenteService.updateDipendeteById(id, request), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<EntityIdResponse> deleteById(@PathVariable Long id){
        dipendenteService.removeDipendenteById(id);
        return new ResponseEntity<>(
                new EntityIdResponse(id),HttpStatus.OK);
    }

}
