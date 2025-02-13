 package com.azienda.dipendenti.controllers;

 import com.azienda.dipendenti.dtos.request.PosizioneLavorativaRequestInsert;
 import com.azienda.dipendenti.dtos.request.PosizioneLavorativaRequestUpdate;
 import com.azienda.dipendenti.dtos.response.PosizioneLavorativaResponse;
 import com.azienda.dipendenti.entities.PosizioneLavorativa;
 import com.azienda.dipendenti.services.PosizioneLavorativaService;
 import jakarta.validation.Valid;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.*;

 import java.util.List;

 @RestController
@RequestMapping("/app/v1/posizionelavorativa")
@CrossOrigin(origins = "http://localhost:4200")
public class PosizioneLavorativaController {
    @Autowired
    private PosizioneLavorativaService posizioneLavorativaService;

    @GetMapping("/get/{id}")
    public ResponseEntity<PosizioneLavorativa> getById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(posizioneLavorativaService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PosizioneLavorativa>> getAll(){
        return  new ResponseEntity<>(posizioneLavorativaService.getAll(),HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<PosizioneLavorativaResponse> update(@PathVariable Long id, @RequestBody @Valid PosizioneLavorativaRequestUpdate request) throws Exception {
        return new ResponseEntity<>(posizioneLavorativaService.updatePosizioneById(id, request), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<PosizioneLavorativaResponse> create(@RequestBody @Valid PosizioneLavorativaRequestInsert request){
        return new ResponseEntity<>(posizioneLavorativaService.create(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PosizioneLavorativaResponse> deleteById(@PathVariable Long id){
        posizioneLavorativaService.deleteById(id);
        return new ResponseEntity<>(
                new PosizioneLavorativaResponse(id),HttpStatus.OK);
    }

}
