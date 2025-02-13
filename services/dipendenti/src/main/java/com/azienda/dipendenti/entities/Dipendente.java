package com.azienda.dipendenti.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Dipendente {
    @Id
    @GeneratedValue
    private Long Id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cognome;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private LocalDate data_nascita;
    @Column(nullable = false)
    private String luogo_nascita;
    @Column(nullable = false, unique = true)
    private String telefono;
    private String immagine_profilo;
    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @CreatedBy
    @Column(name = "created_by")
    private Long createdBy;
    @ManyToOne
    @JoinColumn(name = "id_dipartimento")
    private Dipartimento dipartimento;
    @OneToMany(mappedBy = "dipendente")
    private List<Long> commenti;
    @OneToMany(mappedBy = "dipendente")
    private Set<Long> newses;
    @OneToMany(mappedBy = "dipendente")
    private Set<Long> comunicazioni_aziendali;
    @ManyToOne()
    @JoinColumn(name = "id_posizione")
    private PosizioneLavorativa posizioneLavorativa;
    @ManyToOne()
    @JoinColumn(name = "id_tibratura")
    private Timbratura timbratura;
    @Column(name = "last_login")
    private LocalDateTime lastLogin;
    @LastModifiedDate
    @Column(name = "last_modified_at")
    private LocalDateTime lastModifiedAt;
    @LastModifiedBy
    @Column(name = "last_modified_by")
    private Long lastModifiedBy;

}
