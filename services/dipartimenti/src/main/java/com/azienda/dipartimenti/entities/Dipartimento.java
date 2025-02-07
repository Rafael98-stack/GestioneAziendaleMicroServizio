package com.azienda.dipartimenti.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dipartimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descrizione;
    @ManyToOne
    @JoinColumn(name = "id_posizione")
    private List<Long> posizioniLavorative = new ArrayList<>();

}
