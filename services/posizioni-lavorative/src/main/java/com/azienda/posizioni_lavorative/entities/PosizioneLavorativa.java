package com.azienda.posizioni_lavorative.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class PosizioneLavorativa {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String descrizione;

    // Da ricordare che mappedBy vuole esattamente il nome della variabile presente nell'altra entità in cui è influenzata la relazione
    // Come nel nostro caso nell'entità Dipartimento:  private Set<PosizioneLavorativa> posizioniLavorative = new HashSet<>();
    @OneToMany(mappedBy = "posizioniLavorative")
    private List<Long> dipartimenti;

    @OneToMany(mappedBy = "posizione_lavorativa")
    private List<Long> dipendenti;

}

