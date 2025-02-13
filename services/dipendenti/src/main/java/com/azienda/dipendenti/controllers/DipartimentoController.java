package com.azienda.dipendenti.controllers;

import com.azienda.dipendenti.dtos.request.DipartimentoRequestInsert;
import com.azienda.dipendenti.dtos.request.DipartimentoRequestUpdate;
import com.azienda.dipendenti.dtos.response.DipartimentoResponse;
import com.azienda.dipendenti.entities.Dipartimento;
import com.azienda.dipendenti.services.DipartimentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/v1/Dipartimento")
@CrossOrigin(origins = "http://localhost:4200")
public class DipartimentoController {

    @Autowired
    DipartimentoService dipartimentoService;

    @GetMapping("/get/{id}")
    public ResponseEntity<Dipartimento> getById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(dipartimentoService.getDipartimentoById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Dipartimento>> getAll(){
        return  new ResponseEntity<>(dipartimentoService.getAllDipartimenti(),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<DipartimentoResponse> create(@RequestBody @Valid DipartimentoRequestInsert request){
        return new ResponseEntity<>(dipartimentoService.insertDipartimento(request), HttpStatus.CREATED);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<DipartimentoResponse> update(@PathVariable Long id, @RequestBody @Valid DipartimentoRequestUpdate request) throws Exception {
        return new ResponseEntity<>(dipartimentoService.updateDipartimentoById(id, request), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DipartimentoResponse> deleteById(@PathVariable Long id){
        dipartimentoService.removeDipartimentoById(id);
        return new ResponseEntity<>(
                new DipartimentoResponse(id),HttpStatus.OK);
    }
}
