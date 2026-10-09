package com.example.util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class HibernateStartupListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            HibernateUtil.initialize();
            event.getServletContext().setAttribute("databaseAvailable", true);
        } catch (RuntimeException e) {
            event.getServletContext().setAttribute("databaseAvailable", false);
            event.getServletContext().log("L'application a demarre, mais la base de donnees MySQL est indisponible.", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        try {
            HibernateUtil.shutdown();
        } catch (RuntimeException e) {
            event.getServletContext().log("Erreur pendant la fermeture Hibernate.", e);
        }
    }
}
