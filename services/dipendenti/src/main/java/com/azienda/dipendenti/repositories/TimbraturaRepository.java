package com.azienda.dipendenti.repositories;

import com.azienda.dipendenti.entities.Timbratura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import java.time.LocalDate;
import java.util.List;

@Repository

public interface TimbraturaRepository extends JpaRepository<Timbratura,Long> {

    List<Timbratura> findByOrarioInizioBetween(LocalDateTime start, LocalDateTime end);
    List<Timbratura> findBydata_corrente(LocalDate data);
}
