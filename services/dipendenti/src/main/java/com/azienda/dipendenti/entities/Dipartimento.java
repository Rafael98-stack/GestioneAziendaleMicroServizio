package com.azienda.dipendenti.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder

public class Dipartimento {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String descrizione;

    @OneToMany (mappedBy = "dipartimento")
    private List<Dipendente> dipendenti = new ArrayList<>();

    @OneToMany (mappedBy = "dipartimento")
    private List<PosizioneLavorativa> posizioniLavorative = new ArrayList<>();
}
