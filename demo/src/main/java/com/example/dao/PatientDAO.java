package com.example.dao;

import com.example.model.Patient;

import java.util.List;
import java.util.Optional;

public class PatientDAO extends GenericDAO<Patient> {

    public PatientDAO() {
        super(Patient.class);
    }

    public Optional<Patient> findByNumeroSocial(String numeroSocial) {
        return executeRead(session ->
                session.createQuery(
                                "FROM Patient p WHERE p.numeroSocial = :numero",
                                Patient.class
                        )
                        .setParameter("numero", numeroSocial)
                        .uniqueResultOptional()
        );
    }

    public List<Patient> searchByNom(String nom) {
        return executeRead(session ->
                session.createQuery(
                                "FROM Patient p WHERE LOWER(p.nom) LIKE :nom ORDER BY p.nom",
                                Patient.class
                        )
                        .setParameter("nom", "%" + nom.toLowerCase() + "%")
                        .getResultList()
        );
    }
}