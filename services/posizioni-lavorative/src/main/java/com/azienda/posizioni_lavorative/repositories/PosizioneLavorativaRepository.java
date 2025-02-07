package com.azienda.posizioni_lavorative.repositories;

import com.azienda.posizioni_lavorative.entities.PosizioneLavorativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PosizioneLavorativaRepository extends JpaRepository<PosizioneLavorativa,Long> {
}
