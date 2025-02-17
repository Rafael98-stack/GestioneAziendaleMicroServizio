package com.azienda.dipendenti.services;

import com.azienda.dipendenti.entities.Curriculum;
import com.azienda.dipendenti.repositories.CurriculumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class CurriculumService {
    private static final String UPLOAD_DIR = "curricula";
    @Autowired
    CurriculumRepository curriculumRepository;
    @Autowired
    DipendenteService dipendenteService;

    public Curriculum getById(Long id) throws Exception {
        return curriculumRepository.findById(id).orElseThrow(() -> new Exception("Curriculum con id " + id + " non esiste"));
    }

    public Curriculum getByIdDipendente(Long id) throws Exception {
        return curriculumRepository.findByIdDipendente(id).orElseThrow(() -> new Exception("Curriculum con idDipendente " + id + " non esiste"));
    }

    public List<Curriculum> getAll(){
        return curriculumRepository.findAll();
    }

    public Curriculum caricaCV(Long idDipendente, MultipartFile file) throws IOException {
        dipendenteService.getDipendenteById(idDipendente);

        if (file.isEmpty()) {
            throw new IllegalArgumentException("Il file è vuoto!");
        }

        if (!file.getContentType().equals("application/pdf") &&
                !file.getContentType().equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
            throw new IllegalArgumentException("Formato non supportato! Solo PDF o DOCX.");
        }

        Path uploadPath = Paths.get(UPLOAD_DIR).toAbsolutePath().normalize();
        File uploadDir = new File(uploadPath.toString());
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        Optional<Curriculum> existingCv = curriculumRepository.findByIdDipendente(idDipendente);

        String filename = "cv_" + idDipendente + "_" + file.getOriginalFilename();
        File destFile = new File(uploadPath.toString(), filename);
        file.transferTo(destFile);

        Curriculum curriculum;
        if (existingCv.isPresent()) {
            curriculum = existingCv.get();
            curriculum.setFilePath(destFile.getAbsolutePath());
            curriculum.setFileType(file.getContentType());
        } else {
            curriculum = new Curriculum();
            curriculum.setIdDipendente(idDipendente);
            curriculum.setFilePath(destFile.getAbsolutePath());
            curriculum.setFileType(file.getContentType());
        }

        return curriculumRepository.save(curriculum);
    }

    public Resource downloadCv(Long idDipendente) throws Exception {
        dipendenteService.getDipendenteById(idDipendente);
        Curriculum curriculum = curriculumRepository
                .findByIdDipendente(idDipendente)
                .orElseThrow(() -> new Exception("CV non trovato"));
        Path filePath = Paths
                .get(curriculum.getFilePath())
                .toAbsolutePath()
                .normalize();
        return new UrlResource(filePath.toUri());
    }

    public void deleteCv(Long idDipendente) throws Exception {
        Curriculum curriculum = curriculumRepository.findByIdDipendente(idDipendente).orElseThrow(() -> new Exception("CV non trovato"));

        // Eliminare il file dal filesystem
        File file = new File(curriculum.getFilePath());
        if (file.exists()) {
            file.delete();
        }

        // Eliminare il record dal database
        curriculumRepository.delete(curriculum);
    }
}
