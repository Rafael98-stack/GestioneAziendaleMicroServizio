package com.azienda.dipendenti.repositories;

import com.azienda.dipendenti.entities.Dipartimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DipartimentoRepository extends JpaRepository<Dipartimento,Long> {
}
