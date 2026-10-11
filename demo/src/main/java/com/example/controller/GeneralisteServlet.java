package com.example.controller;

import com.example.model.Consultation;
import com.example.model.CreneauHoraire;
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

        setNoCacheHeaders(response);

        HttpSession session = request.getSession(false);

        if (!isGeneraliste(session)) {
            response.sendRedirect(
                    request.getContextPath() + "/login.jsp"
            );
            return;
        }

        try {

            List<Consultation> fileAttente =
                    generalisteService.getFileAttente();

            request.setAttribute(
                    "fileAttente",
                    fileAttente
            );


            String consultationIdParam =
                    request.getParameter("consultationId");

            if (consultationIdParam != null
                    && !consultationIdParam.isBlank()) {

                Long consultationId =
                        Long.parseLong(consultationIdParam);

                Consultation consultationActive =
                        generalisteService
                                .getConsultationById(
                                        consultationId
                                );

                request.setAttribute(
                        "consultationActive",
                        consultationActive
                );
            }


            String specialiteParam =
                    request.getParameter("specialite");

            if (specialiteParam != null
                    && !specialiteParam.isBlank()) {

                Enums.Specialite specialite =
                        Enums.Specialite.valueOf(
                                specialiteParam
                        );

                List<Utilisateur> specialistes =
                        generalisteService
                                .chercherSpecialistes(
                                        specialite
                                );

                request.setAttribute(
                        "specialistes",
                        specialistes
                );

                request.setAttribute(
                        "specialiteSelectionnee",
                        specialite
                );
            }


            String specialisteIdParam =
                    request.getParameter("specialisteId");

            if (specialisteIdParam != null
                    && !specialisteIdParam.isBlank()) {

                Long specialisteId =
                        Long.parseLong(
                                specialisteIdParam
                        );

                List<CreneauHoraire> creneaux =
                        generalisteService
                                .getCreneauxDisponibles(
                                        specialisteId
                                );

                request.setAttribute(
                        "creneaux",
                        creneaux
                );

                request.setAttribute(
                        "specialisteIdSelectionne",
                        specialisteId
                );
            }


            request.getRequestDispatcher(
                    "/WEB-INF/generaliste.jsp"
            ).forward(
                    request,
                    response
            );

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                            + "/generaliste?error=invalid-data"
            );
        }
    }


    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        setNoCacheHeaders(response);

        HttpSession session =
                request.getSession(false);

        if (!isGeneraliste(session)) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/login.jsp"
            );

            return;
        }

        Utilisateur generaliste =
                (Utilisateur) session
                        .getAttribute(
                                "utilisateur"
                        );

        String action =
                request.getParameter(
                        "action"
                );

        try {

            if ("commencer".equals(action)) {

                Long consultationId =
                        Long.parseLong(
                                request.getParameter(
                                        "consultationId"
                                )
                        );

                String motif =
                        request.getParameter(
                                "motif"
                        );

                String observations =
                        request.getParameter(
                                "observations"
                        );

                String symptomes =
                        request.getParameter(
                                "symptomes"
                        );

                String examenClinique =
                        request.getParameter(
                                "examenClinique"
                        );

                generalisteService
                        .commencerConsultation(
                                consultationId,
                                generaliste,
                                motif,
                                observations,
                                symptomes,
                                examenClinique
                        );

                response.sendRedirect(
                        request.getContextPath()
                                + "/generaliste?consultationId="
                                + consultationId
                );

                return;
            }


            else if ("cloturer".equals(action)) {

                Long consultationId =
                        Long.parseLong(
                                request.getParameter(
                                        "consultationId"
                                )
                        );

                String diagnostic =
                        request.getParameter(
                                "diagnostic"
                        );

                String medicaments =
                        request.getParameter(
                                "medicaments"
                        );

                generalisteService
                        .cloturerConsultation(
                                consultationId,
                                diagnostic,
                                medicaments
                        );

                response.sendRedirect(
                        request.getContextPath()
                                + "/generaliste"
                );

                return;
            }


            else if ("demanderExpertise".equals(action)) {

                Long consultationId =
                        Long.parseLong(
                                request.getParameter(
                                        "consultationId"
                                )
                        );

                Long specialisteId =
                        Long.parseLong(
                                request.getParameter(
                                        "specialisteId"
                                )
                        );

                Long creneauId =
                        Long.parseLong(
                                request.getParameter(
                                        "creneauId"
                                )
                        );

                String question =
                        request.getParameter(
                                "question"
                        );

                String donneesAnalyses =
                        request.getParameter(
                                "donneesAnalyses"
                        );

                Enums.Priorite priorite =
                        Enums.Priorite.valueOf(
                                request.getParameter(
                                        "priorite"
                                )
                        );

                Enums.TypeExpertise typeExpertise =
                        Enums.TypeExpertise.valueOf(
                                request.getParameter(
                                        "typeExpertise"
                                )
                        );

                generalisteService
                        .demanderExpertise(
                                consultationId,
                                specialisteId,
                                creneauId,
                                question,
                                donneesAnalyses,
                                priorite,
                                typeExpertise
                        );

                response.sendRedirect(
                        request.getContextPath()
                                + "/generaliste"
                );

                return;
            }


            response.sendRedirect(
                    request.getContextPath()
                            + "/generaliste"
            );

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                            + "/generaliste?error=invalid-data"
            );
        }
    }


    private boolean isGeneraliste(
            HttpSession session
    ) {

        if (session == null) {
            return false;
        }

        Utilisateur utilisateur =
                (Utilisateur) session
                        .getAttribute(
                                "utilisateur"
                        );

        return utilisateur != null
                && utilisateur.getRole()
                == Enums.Role.GENERALISTE;
    }

    private void setNoCacheHeaders(
            HttpServletResponse response
    ) {

        response.setHeader(
                "Cache-Control",
                "no-cache, no-store, must-revalidate"
        );
        response.setHeader(
                "Pragma",
                "no-cache"
        );
        response.setDateHeader(
                "Expires",
                0
        );
    }
}
