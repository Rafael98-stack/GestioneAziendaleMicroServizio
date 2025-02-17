package com.azienda.dipendenti.repositories;

import com.azienda.dipendenti.entities.Curriculum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum,Long> {
    Optional<Curriculum> findByIdDipendente(Long idDipendente);
}
