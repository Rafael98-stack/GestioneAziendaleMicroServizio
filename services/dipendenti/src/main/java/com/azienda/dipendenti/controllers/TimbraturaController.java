package com.azienda.dipendenti.controllers;

import com.azienda.dipendenti.dtos.request.TimbraturaRequestRegister;
import com.azienda.dipendenti.dtos.request.TimbraturaRequestUpdate;
import com.azienda.dipendenti.dtos.response.EntityIdResponse;
import com.azienda.dipendenti.dtos.response.TimbraturaResponse;
import com.azienda.dipendenti.services.TimbraturaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/v1/Timbratura")
@CrossOrigin(origins = "http://localhost:8072")
public class TimbraturaController {
    @Autowired
    TimbraturaService timbraturaService;

    @GetMapping("/get/{id}")
    public ResponseEntity<TimbraturaResponse> getById(@PathVariable Long id){
        return new ResponseEntity<>(timbraturaService.getTimbraturaByIdToResponse(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TimbraturaResponse>> getAll(){
        return  new ResponseEntity<>(timbraturaService.getAllTimbratura(),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<EntityIdResponse> create(@RequestBody @Valid TimbraturaRequestRegister request){
        return new ResponseEntity<>(timbraturaService.registerTimbratura(request), HttpStatus.CREATED);
    }


    @PutMapping("/update")
public ResponseEntity<TimbraturaResponse> update( @RequestBody @Valid TimbraturaRequestUpdate request) throws Exception {
        return new ResponseEntity<>(timbraturaService.updateTimbraturaById(request), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<EntityIdResponse> deleteById(@PathVariable Long id){
        timbraturaService.removeTimbraturaById(id);
        return new ResponseEntity<>(
                new EntityIdResponse(id),HttpStatus.OK);
    }
}
