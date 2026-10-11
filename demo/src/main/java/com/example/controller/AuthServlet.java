package com.example.controller;

import com.example.model.Utilisateur;
import com.example.service.AuthService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {

    @Inject
    private AuthService authService;

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String email = request.getParameter("email");
        String motDePasse = request.getParameter("motDePasse");

        Optional<Utilisateur> utilisateurOpt =
                authService.login(email, motDePasse);

        if (utilisateurOpt.isEmpty()) {
            response.sendRedirect(
                    request.getContextPath() + "/login.jsp?error=invalid"
            );
            return;
        }

        Utilisateur utilisateur = utilisateurOpt.get();

        HttpSession session = request.getSession();

        session.setAttribute("utilisateur", utilisateur);
        session.setAttribute("role", utilisateur.getRole());

        switch (utilisateur.getRole()) {

            case INFIRMIER:
                response.sendRedirect(
                        request.getContextPath() + "/infirmier.jsp"
                );
                break;

            case GENERALISTE:
                response.sendRedirect(
                        request.getContextPath() + "/generaliste"
                );
                break;

            case SPECIALISTE:
                response.sendRedirect(
                        request.getContextPath() + "/specialiste.jsp"
                );
                break;
        }
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        response.sendRedirect(
                request.getContextPath() + "/login.jsp"
        );
    }
}
