package com.example.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "creneaux_horaires")
public class CreneauHoraire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dateHeureDebut;

    @Column(nullable = false)
    private LocalDateTime dateHeureFin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Enums.StatutCreneau statut;

    @ManyToOne(optional = false)
    @JoinColumn(name = "specialiste_id")
    private Utilisateur specialiste;

    @OneToOne
    @JoinColumn(name = "demande_expertise_id")
    private DemandeExpertise demandeExpertise;

    public CreneauHoraire() {
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateHeureDebut() {
        return dateHeureDebut;
    }

    public void setDateHeureDebut(LocalDateTime dateHeureDebut) {
        this.dateHeureDebut = dateHeureDebut;
    }

    public LocalDateTime getDateHeureFin() {
        return dateHeureFin;
    }

    public void setDateHeureFin(LocalDateTime dateHeureFin) {
        this.dateHeureFin = dateHeureFin;
    }

    public Enums.StatutCreneau getStatut() {
        return statut;
    }

    public void setStatut(Enums.StatutCreneau statut) {
        this.statut = statut;
    }

    public Utilisateur getSpecialiste() {
        return specialiste;
    }

    public void setSpecialiste(Utilisateur specialiste) {
        this.specialiste = specialiste;
    }

    public DemandeExpertise getDemandeExpertise() {
        return demandeExpertise;
    }

    public void setDemandeExpertise(DemandeExpertise demandeExpertise) {
        this.demandeExpertise = demandeExpertise;
    }
}