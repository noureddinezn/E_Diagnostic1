# Persistence Layer

Hibernate ORM is configured through `hibernate.cfg.xml`.

The mapped entities are:

- `Utilisateur`
- `Patient`
- `Consultation`
- `DemandeExpertise`
- `CreneauHoraire`
- `Ordonnance`

DAO classes centralize database access so controllers and services do not work directly with Hibernate sessions.
