package com.example.controller;

import com.example.model.DemandeExpertise;
import com.example.model.Enums;
import com.example.model.Utilisateur;
import com.example.service.SpecialisteService;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/specialiste")
public class SpecialisteServlet extends HttpServlet {

    @Inject
    private SpecialisteService specialisteService;

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
                utilisateur.getRole() != Enums.Role.SPECIALISTE) {

            response.sendRedirect(
                    request.getContextPath() + "/login.jsp"
            );
            return;
        }

        Long specialisteId = utilisateur.getId();

        List<DemandeExpertise> demandes =
                specialisteService.getDemandes(specialisteId);

        request.setAttribute("demandes", demandes);

        request.setAttribute(
                "creneaux",
                specialisteService.getCreneaux(specialisteId)
        );

        request.getRequestDispatcher("/specialiste.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        String action = request.getParameter("action");

        if ("repondre".equals(action)) {

            Long demandeId =
                    Long.parseLong(
                            request.getParameter("demandeId")
                    );

            String avis =
                    request.getParameter("avis");

            String recommandations =
                    request.getParameter("recommandations");

            specialisteService.repondreExpertise(
                    demandeId,
                    avis,
                    recommandations
            );
        }

        response.sendRedirect(
                request.getContextPath() + "/specialiste"
        );
    }
}