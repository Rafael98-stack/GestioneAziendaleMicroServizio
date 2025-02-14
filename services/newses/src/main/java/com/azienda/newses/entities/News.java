package com.azienda.newses.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class News
{
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titolo;
    private String contenuto;
    private Long likes = 0L;
    private String immagine;

 public News(String contenuto, String titolo, String immagine, Long dipendente) {
  this.contenuto = contenuto;
  this.titolo = titolo;
  this.immagine = immagine;
  this.dipendente = dipendente;
 }

    private Long dipendente;

    @OneToMany
    @JoinColumn(name = "id_commento")
    private List<Commento> commenti = new ArrayList<>();

 public News(String immagine, String titolo, String contenuto, Long aLong, Long like) {
 }
}
