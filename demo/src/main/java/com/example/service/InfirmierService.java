package com.example.service;

import com.example.dao.ConsultationDAO;
import com.example.dao.PatientDAO;
import com.example.model.Consultation;
import com.example.model.Enums;
import com.example.model.Patient;

import jakarta.inject.Inject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class InfirmierService {

    private final PatientDAO patientDAO;
    private final ConsultationDAO consultationDAO;

    @Inject
    public InfirmierService(
            PatientDAO patientDAO,
            ConsultationDAO consultationDAO
    ) {
        this.patientDAO = patientDAO;
        this.consultationDAO = consultationDAO;
    }

    public Optional<Patient> rechercherPatient(String numeroSocial) {
        return patientDAO.findByNumeroSocial(numeroSocial);
    }

    public Patient creerPatient(Patient patient) {
        patientDAO.save(patient);
        return patient;
    }

    public Consultation ajouterEnFileAttente(
            Patient patient,
            String tension,
            Integer frequenceCardiaque,
            Double temperature,
            Integer frequenceRespiratoire,
            Double poids,
            Double taille
    ) {

        Consultation consultation = new Consultation();

        consultation.setPatient(patient);
        consultation.setDateCreation(LocalDateTime.now());

        consultation.setTensionArterielle(tension);
        consultation.setFrequenceCardiaque(frequenceCardiaque);
        consultation.setTemperature(temperature);
        consultation.setFrequenceRespiratoire(frequenceRespiratoire);
        consultation.setPoids(poids);
        consultation.setTaille(taille);

        consultation.setStatut(
                Enums.StatutConsultation.EN_FILE_ATTENTE
        );

        consultationDAO.save(consultation);

        return consultation;
    }

    public List<Consultation> getPatientsDuJour() {

        LocalDate today = LocalDate.now();

        return consultationDAO.findAll()
                .stream()
                .filter(c ->
                        c.getDateCreation() != null &&
                        c.getDateCreation()
                                .toLocalDate()
                                .equals(today)
                )
                .sorted((c1, c2) ->
                        c1.getDateCreation()
                                .compareTo(c2.getDateCreation())
                )
                .toList();
    }
}
