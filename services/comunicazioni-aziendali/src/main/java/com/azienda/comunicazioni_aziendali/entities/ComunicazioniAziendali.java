package com.azienda.comunicazioni_aziendali.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComunicazioniAziendali
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titolo;
    private String contenuto;

    @ManyToOne
    @JoinColumn(name = "id_dipendente")
    private Long dipendente;


}
