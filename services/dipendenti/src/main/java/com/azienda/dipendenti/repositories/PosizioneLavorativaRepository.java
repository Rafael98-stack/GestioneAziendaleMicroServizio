package com.azienda.dipendenti.repositories;

import com.azienda.dipendenti.entities.PosizioneLavorativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PosizioneLavorativaRepository extends JpaRepository<PosizioneLavorativa,Long> {

}
