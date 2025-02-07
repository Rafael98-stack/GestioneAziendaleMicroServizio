package com.azienda.dipartimenti.repositories;

import com.azienda.dipartimenti.entities.Dipartimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DipartimentoRepository extends JpaRepository<Dipartimento,Long> {
}