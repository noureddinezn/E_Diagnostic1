<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.DemandeExpertise" %>
<%@ page import="com.example.model.CreneauHoraire" %>

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>Espace Specialiste</title>

    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-100 min-h-screen">

<div class="max-w-6xl mx-auto p-6">

    <div class="flex justify-between items-center mb-6">

        <h1 class="text-3xl font-bold text-blue-700">
            Espace Specialiste
        </h1>

        <a
            href="${pageContext.request.contextPath}/auth"
            class="bg-red-500 text-white px-4 py-2 rounded"
        >
            Logout
        </a>

    </div>


    <h2 class="text-xl font-bold mb-4">
        Mes demandes d'expertise
    </h2>


    <%
        List<DemandeExpertise> demandes =
                (List<DemandeExpertise>) request.getAttribute("demandes");

        if (demandes != null && !demandes.isEmpty()) {

            for (DemandeExpertise demande : demandes) {
    %>

    <div class="bg-white shadow rounded p-5 mb-5">

        <p>
            <strong>Question :</strong>
            <%= demande.getQuestion() %>
        </p>

        <p>
            <strong>Priorite :</strong>
            <%= demande.getPriorite() %>
        </p>

        <p>
            <strong>Statut :</strong>
            <%= demande.getStatut() %>
        </p>

        <p>
            <strong>Date :</strong>
            <%= demande.getDateDemande() %>
        </p>


        <% if (demande.getStatut() != null &&
                demande.getStatut().name().equals("EN_ATTENTE")) { %>

        <form
            action="${pageContext.request.contextPath}/specialiste"
            method="POST"
            class="mt-4"
        >

            <input
                type="hidden"
                name="action"
                value="repondre"
            >

            <input
                type="hidden"
                name="demandeId"
                value="<%= demande.getId() %>"
            >


            <div class="mb-3">

                <label class="block font-semibold mb-1">
                    Avis medical
                </label>

                <textarea
                    name="avis"
                    required
                    class="w-full border rounded p-2"
                ></textarea>

            </div>


            <div class="mb-3">

                <label class="block font-semibold mb-1">
                    Recommandations
                </label>

                <textarea
                    name="recommandations"
                    required
                    class="w-full border rounded p-2"
                ></textarea>

            </div>


            <button
                type="submit"
                class="bg-blue-600 text-white px-4 py-2 rounded"
            >
                Repondre
            </button>

        </form>

        <% } else { %>

        <div class="mt-4 bg-green-100 p-3 rounded">

            <p>
                <strong>Avis :</strong>
                <%= demande.getAvisSpecialiste() %>
            </p>

            <p>
                <strong>Recommandations :</strong>
                <%= demande.getRecommandations() %>
            </p>

        </div>

        <% } %>

    </div>

    <%
            }

        } else {
    %>

    <div class="bg-white p-5 rounded shadow">
        Aucune demande disponible.
    </div>

    <%
        }
    %>


    <h2 class="text-xl font-bold mt-8 mb-4">
        Mes creneaux
    </h2>

    <%
        List<CreneauHoraire> creneaux =
                (List<CreneauHoraire>) request.getAttribute("creneaux");

        if (creneaux != null && !creneaux.isEmpty()) {

            for (CreneauHoraire creneau : creneaux) {
    %>

    <div class="bg-white shadow rounded p-4 mb-3">

        <p>
            <strong>Debut :</strong>
            <%= creneau.getDateHeureDebut() %>
        </p>

        <p>
            <strong>Fin :</strong>
            <%= creneau.getDateHeureFin() %>
        </p>

        <p>
            <strong>Statut :</strong>
            <%= creneau.getStatut() %>
        </p>

    </div>

    <%
            }

        } else {
    %>

    <div class="bg-white p-5 rounded shadow">
        Aucun creneau disponible.
    </div>

    <%
        }
    %>

</div>

</body>
</html>