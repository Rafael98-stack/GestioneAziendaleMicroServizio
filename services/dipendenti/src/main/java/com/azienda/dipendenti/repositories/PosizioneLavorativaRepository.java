package com.azienda.dipendenti.repositories;

import com.azienda.dipendenti.entities.PosizioneLavorativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface PosizioneLavorativaRepository extends JpaRepository<PosizioneLavorativa,Long> {

    @Query("SELECT p.nome FROM Posizione p WHERE p.id = :id")
    String getType(Long id);
}
