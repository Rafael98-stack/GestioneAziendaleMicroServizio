package com.azienda.dipendenti.entities;

import jakarta.persistence.*;
import lombok.*;

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
    @ManyToOne
    @JoinColumn(name = "id_dipartimento")
    private Dipartimento dipartimento;

    @OneToMany(mappedBy = "posizioneLavorativa")
    private List<Dipendente> dipendenti;
}

