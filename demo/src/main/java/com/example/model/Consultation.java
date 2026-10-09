package com.example.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "consultations")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreation;

    private String tensionArterielle;
    private Integer frequenceCardiaque;
    private Double temperature;
    private Integer frequenceRespiratoire;
    private Double poids;
    private Double taille;

    @Column(length = 2000)
    private String motif;

    @Column(length = 2000)
    private String observations;

    @Column(length = 2000)
    private String symptomes;

    @Column(length = 2000)
    private String examenClinique;

    @Column(length = 2000)
    private String diagnostic;

    private Double coutConsultation = 150.0;

    @Enumerated(EnumType.STRING)
    private Enums.StatutConsultation statut;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "generaliste_id")
    private Utilisateur generaliste;

    public Consultation() {
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getTensionArterielle() {
        return tensionArterielle;
    }

    public void setTensionArterielle(String tensionArterielle) {
        this.tensionArterielle = tensionArterielle;
    }

    public Integer getFrequenceCardiaque() {
        return frequenceCardiaque;
    }

    public void setFrequenceCardiaque(Integer frequenceCardiaque) {
        this.frequenceCardiaque = frequenceCardiaque;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getFrequenceRespiratoire() {
        return frequenceRespiratoire;
    }

    public void setFrequenceRespiratoire(Integer frequenceRespiratoire) {
        this.frequenceRespiratoire = frequenceRespiratoire;
    }

    public Double getPoids() {
        return poids;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
    }

    public Double getTaille() {
        return taille;
    }

    public void setTaille(Double taille) {
        this.taille = taille;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getSymptomes() {
        return symptomes;
    }

    public void setSymptomes(String symptomes) {
        this.symptomes = symptomes;
    }

    public String getExamenClinique() {
        return examenClinique;
    }

    public void setExamenClinique(String examenClinique) {
        this.examenClinique = examenClinique;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public Double getCoutConsultation() {
        return coutConsultation;
    }

    public void setCoutConsultation(Double coutConsultation) {
        this.coutConsultation = coutConsultation;
    }

    public Enums.StatutConsultation getStatut() {
        return statut;
    }

    public void setStatut(Enums.StatutConsultation statut) {
        this.statut = statut;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Utilisateur getGeneraliste() {
        return generaliste;
    }

    public void setGeneraliste(Utilisateur generaliste) {
        this.generaliste = generaliste;
    }
}