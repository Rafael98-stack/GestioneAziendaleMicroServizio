package com.azienda.dipendenti.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Dipendente{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cognome;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private LocalDate data_nascita;
    @Column(nullable = false)
    private String luogo_nascita;
    @Column(nullable = false, unique = true)
    private String telefono;
    private String immagine_profilo;

    @ManyToOne
    private Long dipartimento;
}
