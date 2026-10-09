package com.example.dao;

import com.example.model.Consultation;
import com.example.model.Enums;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class ConsultationDAO {

    public void save(Consultation consultation) {
        Transaction tx = null;

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            session.persist(consultation);

            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }

            throw new RuntimeException(e);
        }
    }

    public Optional<Consultation> findById(Long id) {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            Consultation consultation =
                    session.find(Consultation.class, id);

            return Optional.ofNullable(consultation);
        }
    }

    public List<Consultation> findAll() {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                            "FROM Consultation",
                            Consultation.class
                    )
                    .getResultList();
        }
    }

    public List<Consultation> findByStatut(
            Enums.StatutConsultation statut
    ) {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                            "FROM Consultation c " +
                                    "WHERE c.statut = :statut " +
                                    "ORDER BY c.dateCreation DESC",
                            Consultation.class
                    )
                    .setParameter("statut", statut)
                    .getResultList();
        }
    }

    public Consultation update(Consultation consultation) {

        Transaction tx = null;

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            Consultation updated =
                    session.merge(consultation);

            tx.commit();

            return updated;

        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }

            throw new RuntimeException(e);
        }
    }

    public void delete(Long id) {

        Transaction tx = null;

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            Consultation consultation =
                    session.find(Consultation.class, id);

            if (consultation != null) {
                session.remove(consultation);
            }

            tx.commit();

        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }

            throw new RuntimeException(e);
        }
    }
}
