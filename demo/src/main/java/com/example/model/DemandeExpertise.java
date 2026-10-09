package com.example.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "demandes_expertise")
public class DemandeExpertise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2000, nullable = false)
    private String question;

    @Column(length = 3000)
    private String donneesAnalyses;

    @Enumerated(EnumType.STRING)
    private Enums.Priorite priorite;

    @Enumerated(EnumType.STRING)
    private Enums.TypeExpertise typeExpertise;

    @Enumerated(EnumType.STRING)
    private Enums.StatutExpertise statut;

    private LocalDateTime dateDemande;

    @Column(length = 3000)
    private String avisSpecialiste;

    @Column(length = 3000)
    private String recommandations;

    private LocalDateTime dateReponse;

    @ManyToOne(optional = false)
    @JoinColumn(name = "consultation_id")
    private Consultation consultation;

    @ManyToOne(optional = false)
    @JoinColumn(name = "specialiste_id")
    private Utilisateur specialiste;

    public DemandeExpertise() {
    }

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDonneesAnalyses() {
        return donneesAnalyses;
    }

    public void setDonneesAnalyses(String donneesAnalyses) {
        this.donneesAnalyses = donneesAnalyses;
    }

    public Enums.Priorite getPriorite() {
        return priorite;
    }

    public void setPriorite(Enums.Priorite priorite) {
        this.priorite = priorite;
    }

    public Enums.TypeExpertise getTypeExpertise() {
        return typeExpertise;
    }

    public void setTypeExpertise(Enums.TypeExpertise typeExpertise) {
        this.typeExpertise = typeExpertise;
    }

    public Enums.StatutExpertise getStatut() {
        return statut;
    }

    public void setStatut(Enums.StatutExpertise statut) {
        this.statut = statut;
    }

    public LocalDateTime getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(LocalDateTime dateDemande) {
        this.dateDemande = dateDemande;
    }

    public String getAvisSpecialiste() {
        return avisSpecialiste;
    }

    public void setAvisSpecialiste(String avisSpecialiste) {
        this.avisSpecialiste = avisSpecialiste;
    }

    public String getRecommandations() {
        return recommandations;
    }

    public void setRecommandations(String recommandations) {
        this.recommandations = recommandations;
    }

    public LocalDateTime getDateReponse() {
        return dateReponse;
    }

    public void setDateReponse(LocalDateTime dateReponse) {
        this.dateReponse = dateReponse;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    public Utilisateur getSpecialiste() {
        return specialiste;
    }

    public void setSpecialiste(Utilisateur specialiste) {
        this.specialiste = specialiste;
    }
}