package com.azienda.dipendenti.controllers;

import com.azienda.dipendenti.entities.Curriculum;
import com.azienda.dipendenti.services.CurriculumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/app/v1/Curriculum")
@CrossOrigin(origins = "http://localhost:8072")
public class CurriculumController {
    @Autowired
    CurriculumService curriculumService;

    @GetMapping("/get/{id}")
    public ResponseEntity<Curriculum> getById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(curriculumService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/getbydipendente/{id}")
    public ResponseEntity<Curriculum> getByIdDipendente(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(curriculumService.getByIdDipendente(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Curriculum>> getAll(){
        return new ResponseEntity<>(curriculumService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/upload/{userId}")
    public ResponseEntity<String> uploadCv(@PathVariable Long userId, @RequestParam("file") MultipartFile file) {
        try {
            curriculumService.caricaCV(userId, file);
            return ResponseEntity.ok("CV caricato con successo!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Errore: " + e.getMessage());
        }
    }

    @GetMapping("/download/{userId}")
    public ResponseEntity<Resource> downloadCv(@PathVariable Long userId) {
        try {
            Resource resource = curriculumService.downloadCv(userId);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteCv(@PathVariable Long userId) {
        try {
            curriculumService.deleteCv(userId);
            return ResponseEntity.ok("CV eliminato con successo!");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
