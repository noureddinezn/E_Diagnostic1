package com.example.service;

import com.example.dao.ConsultationDAO;
import com.example.dao.CreneauHoraireDAO;
import com.example.dao.DemandeExpertiseDAO;
import com.example.dao.OrdonnanceDAO;
import com.example.dao.UtilisateurDAO;

import com.example.model.Consultation;
import com.example.model.CreneauHoraire;
import com.example.model.DemandeExpertise;
import com.example.model.Enums;
import com.example.model.Ordonnance;
import com.example.model.Utilisateur;

import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.util.List;

public class GeneralisteService {

    private final ConsultationDAO consultationDAO;
    private final UtilisateurDAO utilisateurDAO;
    private final CreneauHoraireDAO creneauHoraireDAO;
    private final DemandeExpertiseDAO demandeExpertiseDAO;
    private final OrdonnanceDAO ordonnanceDAO;

    @Inject
    public GeneralisteService(
            ConsultationDAO consultationDAO,
            UtilisateurDAO utilisateurDAO,
            CreneauHoraireDAO creneauHoraireDAO,
            DemandeExpertiseDAO demandeExpertiseDAO,
            OrdonnanceDAO ordonnanceDAO
    ) {
        this.consultationDAO = consultationDAO;
        this.utilisateurDAO = utilisateurDAO;
        this.creneauHoraireDAO = creneauHoraireDAO;
        this.demandeExpertiseDAO = demandeExpertiseDAO;
        this.ordonnanceDAO = ordonnanceDAO;
    }

    public List<Consultation> getFileAttente() {
        return consultationDAO.findByStatut(
                Enums.StatutConsultation.EN_FILE_ATTENTE
        );
    }

    public Consultation commencerConsultation(
            Long consultationId,
            Utilisateur generaliste,
            String motif,
            String observations,
            String symptomes,
            String examenClinique
    ) {
        Consultation consultation = consultationDAO
                .findById(consultationId)
                .orElseThrow(() ->
                        new RuntimeException("Consultation introuvable")
                );

        consultation.setGeneraliste(generaliste);
        consultation.setMotif(motif);
        consultation.setObservations(observations);
        consultation.setSymptomes(symptomes);
        consultation.setExamenClinique(examenClinique);
        consultation.setStatut(
                Enums.StatutConsultation.EN_COURS
        );

        return consultationDAO.update(consultation);
    }

    public void cloturerConsultation(
            Long consultationId,
            String diagnostic,
            String medicaments
    ) {
        Consultation consultation = consultationDAO
                .findById(consultationId)
                .orElseThrow(() ->
                        new RuntimeException("Consultation introuvable")
                );

        consultation.setDiagnostic(diagnostic);
        consultation.setStatut(
                Enums.StatutConsultation.TERMINEE
        );

        consultationDAO.update(consultation);

        if (medicaments != null && !medicaments.isBlank()) {
            Ordonnance ordonnance = new Ordonnance();

            ordonnance.setConsultation(consultation);
            ordonnance.setDatePrescription(LocalDateTime.now());
            ordonnance.setMedicaments(medicaments);

            ordonnanceDAO.save(ordonnance);
        }
    }

    public List<Utilisateur> chercherSpecialistes(
            Enums.Specialite specialite
    ) {
        return utilisateurDAO
                .findSpecialistesBySpecialite(specialite)
                .stream()
                .sorted((s1, s2) ->
                        Double.compare(
                                s1.getTarifConsultation(),
                                s2.getTarifConsultation()
                        )
                )
                .toList();
    }

    public List<CreneauHoraire> getCreneauxDisponibles(
            Long specialisteId
    ) {
        return creneauHoraireDAO
                .findDisponiblesBySpecialiste(specialisteId);
    }

    public void demanderExpertise(
            Long consultationId,
            Long specialisteId,
            Long creneauId,
            String question,
            String donneesAnalyses,
            Enums.Priorite priorite,
            Enums.TypeExpertise typeExpertise
    ) {

        Consultation consultation = consultationDAO
                .findById(consultationId)
                .orElseThrow(() ->
                        new RuntimeException("Consultation introuvable")
                );

        Utilisateur specialiste = utilisateurDAO
                .findById(specialisteId)
                .orElseThrow(() ->
                        new RuntimeException("Specialiste introuvable")
                );

        CreneauHoraire creneau = creneauHoraireDAO
                .findById(creneauId)
                .orElseThrow(() ->
                        new RuntimeException("Creneau introuvable")
                );

        DemandeExpertise demande = new DemandeExpertise();

        demande.setConsultation(consultation);
        demande.setSpecialiste(specialiste);
        demande.setQuestion(question);
        demande.setDonneesAnalyses(donneesAnalyses);
        demande.setPriorite(priorite);
        demande.setTypeExpertise(typeExpertise);
        demande.setDateDemande(LocalDateTime.now());
        demande.setStatut(
                Enums.StatutExpertise.EN_ATTENTE
        );

        demandeExpertiseDAO.save(demande);

        creneau.setStatut(
                Enums.StatutCreneau.RESERVE
        );
        creneau.setDemandeExpertise(demande);

        creneauHoraireDAO.update(creneau);

        consultation.setStatut(
                Enums.StatutConsultation.EN_ATTENTE_AVIS_SPECIALISTE
        );

        consultationDAO.update(consultation);
    }
}