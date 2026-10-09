package com.example.controller;

import com.example.model.Consultation;
import com.example.model.Enums;
import com.example.model.Utilisateur;
import com.example.service.GeneralisteService;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/generaliste")
public class GeneralisteServlet extends HttpServlet {

    @Inject
    private GeneralisteService generalisteService;

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
                utilisateur.getRole() != Enums.Role.GENERALISTE) {

            response.sendRedirect(
                    request.getContextPath() + "/login.jsp"
            );
            return;
        }

        List<Consultation> fileAttente =
                generalisteService.getFileAttente();

        request.setAttribute(
                "fileAttente",
                fileAttente
        );

        request.getRequestDispatcher("/generaliste.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(
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

        Utilisateur generaliste =
                (Utilisateur) session.getAttribute("utilisateur");

        String action = request.getParameter("action");

        if ("commencer".equals(action)) {

            Long consultationId =
                    Long.parseLong(
                            request.getParameter("consultationId")
                    );

            String motif =
                    request.getParameter("motif");

            String observations =
                    request.getParameter("observations");

            String symptomes =
                    request.getParameter("symptomes");

            String examenClinique =
                    request.getParameter("examenClinique");

            generalisteService.commencerConsultation(
                    consultationId,
                    generaliste,
                    motif,
                    observations,
                    symptomes,
                    examenClinique
            );
        }

        else if ("cloturer".equals(action)) {

            Long consultationId =
                    Long.parseLong(
                            request.getParameter("consultationId")
                    );

            String diagnostic =
                    request.getParameter("diagnostic");

            String medicaments =
                    request.getParameter("medicaments");

            generalisteService.cloturerConsultation(
                    consultationId,
                    diagnostic,
                    medicaments
            );
        }

        else if ("demanderExpertise".equals(action)) {

            Long consultationId =
                    Long.parseLong(
                            request.getParameter("consultationId")
                    );

            Long specialisteId =
                    Long.parseLong(
                            request.getParameter("specialisteId")
                    );

            Long creneauId =
                    Long.parseLong(
                            request.getParameter("creneauId")
                    );

            String question =
                    request.getParameter("question");

            String donneesAnalyses =
                    request.getParameter("donneesAnalyses");

            Enums.Priorite priorite =
                    Enums.Priorite.valueOf(
                            request.getParameter("priorite")
                    );

            Enums.TypeExpertise typeExpertise =
                    Enums.TypeExpertise.valueOf(
                            request.getParameter("typeExpertise")
                    );

            generalisteService.demanderExpertise(
                    consultationId,
                    specialisteId,
                    creneauId,
                    question,
                    donneesAnalyses,
                    priorite,
                    typeExpertise
            );
        }

        response.sendRedirect(
                request.getContextPath() + "/generaliste"
        );
    }
}