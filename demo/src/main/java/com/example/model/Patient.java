package com.example.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private LocalDate dateNaissance;

    @Column(unique = true, nullable = false)
    private String numeroSocial;

    private String telephone;

    private String adresse;

    private String mutuelle;

    @Column(length = 2000)
    private String antecedents;

    @Column(length = 2000)
    private String allergies;

    @Column(length = 2000)
    private String traitementsEnCours;

    public Patient() {
    }

    public Patient(
            String nom,
            String prenom,
            LocalDate dateNaissance,
            String numeroSocial,
            String telephone,
            String adresse,
            String mutuelle,
            String antecedents,
            String allergies,
            String traitementsEnCours
    ) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.numeroSocial = numeroSocial;
        this.telephone = telephone;
        this.adresse = adresse;
        this.mutuelle = mutuelle;
        this.antecedents = antecedents;
        this.allergies = allergies;
        this.traitementsEnCours = traitementsEnCours;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getNumeroSocial() {
        return numeroSocial;
    }

    public void setNumeroSocial(String numeroSocial) {
        this.numeroSocial = numeroSocial;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMutuelle() {
        return mutuelle;
    }

    public void setMutuelle(String mutuelle) {
        this.mutuelle = mutuelle;
    }

    public String getAntecedents() {
        return antecedents;
    }

    public void setAntecedents(String antecedents) {
        this.antecedents = antecedents;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getTraitementsEnCours() {
        return traitementsEnCours;
    }

    public void setTraitementsEnCours(String traitementsEnCours) {
        this.traitementsEnCours = traitementsEnCours;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", numeroSocial='" + numeroSocial + '\'' +
                ", telephone='" + telephone + '\'' +
                ", adresse='" + adresse + '\'' +
                '}';
    }
}