package com.azienda.dipendenti.repositories;

import com.azienda.dipendenti.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DipendeteRepository extends JpaRepository<Dipendente,Long> {

}
