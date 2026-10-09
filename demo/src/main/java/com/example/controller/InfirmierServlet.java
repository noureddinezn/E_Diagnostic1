package com.example.controller;

import com.example.model.Consultation;
import com.example.model.Enums;
import com.example.model.Patient;
import com.example.model.Utilisateur;
import com.example.service.InfirmierService;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@WebServlet("/infirmier")
public class InfirmierServlet extends HttpServlet {

    @Inject
    private InfirmierService infirmierService;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect(
                    request.getContextPath() + "/login.jsp"
            );
            return;
        }

        Utilisateur utilisateur =
                (Utilisateur) session.getAttribute("utilisateur");

        if (utilisateur == null ||
                utilisateur.getRole() != Enums.Role.INFIRMIER) {

            response.sendRedirect(
                    request.getContextPath() + "/login.jsp"
            );
            return;
        }

        List<Consultation> patientsDuJour =
                infirmierService.getPatientsDuJour();

        request.setAttribute(
                "patientsDuJour",
                patientsDuJour
        );

        request.getRequestDispatcher("/infirmier.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("rechercher".equals(action)) {

            String numeroSocial =
                    request.getParameter("numeroSocial");

            Optional<Patient> patientOpt =
                    infirmierService.rechercherPatient(numeroSocial);

            request.setAttribute("patientTrouve", patientOpt.orElse(null));

            List<Consultation> patientsDuJour =
                    infirmierService.getPatientsDuJour();

            request.setAttribute(
                    "patientsDuJour",
                    patientsDuJour
            );

            request.getRequestDispatcher("/infirmier.jsp")
                    .forward(request, response);

            return;
        }

        if ("nouveauPatient".equals(action)) {

            Patient patient = new Patient();

            patient.setNom(
                    request.getParameter("nom")
            );

            patient.setPrenom(
                    request.getParameter("prenom")
            );

            patient.setDateNaissance(
                    LocalDate.parse(
                            request.getParameter("dateNaissance")
                    )
            );

            patient.setNumeroSocial(
                    request.getParameter("numeroSocial")
            );

            patient.setTelephone(
                    request.getParameter("telephone")
            );

            patient.setAdresse(
                    request.getParameter("adresse")
            );

            patient.setMutuelle(
                    request.getParameter("mutuelle")
            );

            patient.setAntecedents(
                    request.getParameter("antecedents")
            );

            patient.setAllergies(
                    request.getParameter("allergies")
            );

            patient.setTraitementsEnCours(
                    request.getParameter("traitementsEnCours")
            );

            infirmierService.creerPatient(patient);

            infirmierService.ajouterEnFileAttente(
                    patient,
                    request.getParameter("tension"),
                    Integer.parseInt(
                            request.getParameter("frequenceCardiaque")
                    ),
                    Double.parseDouble(
                            request.getParameter("temperature")
                    ),
                    Integer.parseInt(
                            request.getParameter("frequenceRespiratoire")
                    ),
                    Double.parseDouble(
                            request.getParameter("poids")
                    ),
                    Double.parseDouble(
                            request.getParameter("taille")
                    )
            );
        }

        if ("patientExistant".equals(action)) {

            Patient patient = infirmierService
                    .rechercherPatient(
                            request.getParameter("numeroSocial")
                    )
                    .orElseThrow();

            infirmierService.ajouterEnFileAttente(
                    patient,
                    request.getParameter("tension"),
                    Integer.parseInt(
                            request.getParameter("frequenceCardiaque")
                    ),
                    Double.parseDouble(
                            request.getParameter("temperature")
                    ),
                    Integer.parseInt(
                            request.getParameter("frequenceRespiratoire")
                    ),
                    Double.parseDouble(
                            request.getParameter("poids")
                    ),
                    Double.parseDouble(
                            request.getParameter("taille")
                    )
            );
        }

        response.sendRedirect(
                request.getContextPath() + "/infirmier"
        );
    }
}
