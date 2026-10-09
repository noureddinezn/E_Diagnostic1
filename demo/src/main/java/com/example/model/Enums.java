package com.example.model;

public final class Enums {

    private Enums() {
    }

    public enum Role {
        INFIRMIER,
        GENERALISTE,
        SPECIALISTE
    }

    public enum StatutConsultation {
        EN_FILE_ATTENTE,
        EN_COURS,
        EN_ATTENTE_AVIS_SPECIALISTE,
        TERMINEE
    }

    public enum Priorite {
        URGENTE,
        NORMALE,
        NON_URGENTE
    }

    public enum TypeExpertise {
        SYNCHRONE,
        ASYNCHRONE
    }

    public enum StatutCreneau {
        DISPONIBLE,
        RESERVE,
        PASSE
    }

    public enum StatutExpertise {
        EN_ATTENTE,
        TERMINEE
    }

    public enum Specialite {
        CARDIOLOGIE,
        PNEUMOLOGIE,
        DERMATOLOGIE,
        NEUROLOGIE,
        ENDOCRINOLOGIE
    }
}