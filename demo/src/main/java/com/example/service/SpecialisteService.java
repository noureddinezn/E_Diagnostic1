package com.example.service;

import com.example.dao.CreneauHoraireDAO;
import com.example.dao.DemandeExpertiseDAO;
import com.example.dao.UtilisateurDAO;
import com.example.model.CreneauHoraire;
import com.example.model.DemandeExpertise;
import com.example.model.Enums;
import com.example.model.Utilisateur;

import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.util.List;

public class SpecialisteService {

    private final DemandeExpertiseDAO demandeExpertiseDAO;
    private final CreneauHoraireDAO creneauHoraireDAO;
    private final UtilisateurDAO utilisateurDAO;

    @Inject
    public SpecialisteService(
            DemandeExpertiseDAO demandeExpertiseDAO,
            CreneauHoraireDAO creneauHoraireDAO,
            UtilisateurDAO utilisateurDAO
    ) {
        this.demandeExpertiseDAO = demandeExpertiseDAO;
        this.creneauHoraireDAO = creneauHoraireDAO;
        this.utilisateurDAO = utilisateurDAO;
    }

    // Jib ga3 demandes dyal specialiste
    public List<DemandeExpertise> getDemandes(Long specialisteId) {
        return demandeExpertiseDAO.findBySpecialisteId(specialisteId);
    }

    // Filter b statut + priorite b Stream API
    public List<DemandeExpertise> filtrerDemandes(
            Long specialisteId,
            Enums.StatutExpertise statut,
            Enums.Priorite priorite
    ) {

        return demandeExpertiseDAO.findBySpecialisteId(specialisteId)
                .stream()
                .filter(d -> statut == null || d.getStatut() == statut)
                .filter(d -> priorite == null || d.getPriorite() == priorite)
                .toList();
    }

    // Jib creneaux dyal specialiste
    public List<CreneauHoraire> getCreneaux(Long specialisteId) {
        return creneauHoraireDAO.findBySpecialisteId(specialisteId);
    }

    // Specialiste kayjawb 3la expertise
    public void repondreExpertise(
            Long demandeId,
            String avis,
            String recommandations
    ) {

        DemandeExpertise demande = demandeExpertiseDAO
                .findById(demandeId)
                .orElseThrow(() ->
                        new RuntimeException("Demande expertise introuvable")
                );

        demande.setAvisSpecialiste(avis);
        demande.setRecommandations(recommandations);
        demande.setDateReponse(LocalDateTime.now());
        demande.setStatut(Enums.StatutExpertise.TERMINEE);

        demandeExpertiseDAO.update(demande);
    }

    // Modifier tarif + specialite
    public void configurerProfil(
            Long specialisteId,
            Enums.Specialite specialite,
            Double tarif
    ) {

        Utilisateur specialiste = utilisateurDAO
                .findById(specialisteId)
                .orElseThrow(() ->
                        new RuntimeException("Specialiste introuvable")
                );

        specialiste.setSpecialite(specialite);
        specialiste.setTarifConsultation(tarif);

        utilisateurDAO.update(specialiste);
    }
}