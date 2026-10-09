package com.example.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ordonnances")
public class Ordonnance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime datePrescription;

    @Column(length = 4000, nullable = false)
    private String medicaments;

    @OneToOne(optional = false)
    @JoinColumn(name = "consultation_id", unique = true)
    private Consultation consultation;

    public Ordonnance() {
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDatePrescription() {
        return datePrescription;
    }

    public void setDatePrescription(LocalDateTime datePrescription) {
        this.datePrescription = datePrescription;
    }

    public String getMedicaments() {
        return medicaments;
    }

    public void setMedicaments(String medicaments) {
        this.medicaments = medicaments;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }
}