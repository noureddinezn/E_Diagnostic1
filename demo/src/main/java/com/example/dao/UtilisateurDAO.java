package com.example.dao;

import com.example.model.Enums;
import com.example.model.Utilisateur;

import java.util.List;
import java.util.Optional;

public class UtilisateurDAO extends GenericDAO<Utilisateur> {

    public UtilisateurDAO() {
        super(Utilisateur.class);
    }

    public Optional<Utilisateur> findByEmail(String email) {
        return executeRead(session ->
                session.createQuery(
                                "FROM Utilisateur u WHERE u.email = :email",
                                Utilisateur.class
                        )
                        .setParameter("email", email)
                        .uniqueResultOptional()
        );
    }

    public List<Utilisateur> findByRole(Enums.Role role) {
        return executeRead(session ->
                session.createQuery(
                                "FROM Utilisateur u WHERE u.role = :role",
                                Utilisateur.class
                        )
                        .setParameter("role", role)
                        .getResultList()
        );
    }

    public List<Utilisateur> findSpecialistesBySpecialite(
            Enums.Specialite specialite
    ) {
        return executeRead(session ->
                session.createQuery(
                                "FROM Utilisateur u " +
                                "WHERE u.role = :role " +
                                "AND u.specialite = :specialite",
                                Utilisateur.class
                        )
                        .setParameter("role", Enums.Role.SPECIALISTE)
                        .setParameter("specialite", specialite)
                        .getResultList()
        );
    }
}