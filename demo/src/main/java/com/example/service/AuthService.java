package com.example.service;

import com.example.dao.UtilisateurDAO;
import com.example.model.Utilisateur;
import com.example.util.PasswordUtil;
import jakarta.inject.Inject;

import java.util.Optional;

public class AuthService {

    private final UtilisateurDAO utilisateurDAO;

    @Inject
    public AuthService(UtilisateurDAO utilisateurDAO) {
        this.utilisateurDAO = utilisateurDAO;
    }

    public Optional<Utilisateur> login(String email, String motDePasse) {

        Optional<Utilisateur> utilisateurOpt =
                utilisateurDAO.findByEmail(email);

        if (utilisateurOpt.isEmpty()) {
            return Optional.empty();
        }

        Utilisateur utilisateur = utilisateurOpt.get();

        boolean passwordValide = PasswordUtil.matches(
                motDePasse,
                utilisateur.getMotDePasse()
        );

        if (!passwordValide) {
            return Optional.empty();
        }

        return Optional.of(utilisateur);
    }
}