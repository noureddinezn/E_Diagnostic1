<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>
<%@ page import="com.example.model.DemandeExpertise" %>
<%@ page import="com.example.model.CreneauHoraire" %>
<%@ page import="com.example.model.Enums" %>

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>Espace Specialiste</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css">
</head>

<body class="app-body">

<div class="app-shell">

    <!-- HEADER -->

    <div class="topbar">

        <h1 class="page-title">
            Espace Specialiste
        </h1>

        <a
                href="${pageContext.request.contextPath}/auth"
                class="bg-red-500 text-white px-4 py-2 rounded"
        >
            Logout
        </a>

    </div>


    <!-- ERROR -->

    <%
        String error = request.getParameter("error");

        if (error != null) {
    %>

    <div class="app-alert">
        Une erreur est survenue.
        Verifiez les informations saisies.
    </div>

    <%
        }
    %>


    <!-- FILTRES -->

    <div class="bg-white shadow rounded p-5 mb-6">

        <h2 class="section-title">
            Filtrer les demandes
        </h2>

        <form
                action="${pageContext.request.contextPath}/specialiste"
                method="GET"
        >

            <div class="grid grid-cols-1 md:grid-cols-2 gap-4">

                <div>

                    <label class="block font-semibold mb-1">
                        Statut
                    </label>

                    <select
                            name="statut"
                            class="w-full border rounded p-2"
                    >

                        <option value="">
                            Tous les statuts
                        </option>

                        <option value="EN_ATTENTE">
                            EN ATTENTE
                        </option>

                        <option value="TERMINEE">
                            TERMINEE
                        </option>

                    </select>

                </div>


                <div>

                    <label class="block font-semibold mb-1">
                        Priorite
                    </label>

                    <select
                            name="priorite"
                            class="w-full border rounded p-2"
                    >

                        <option value="">
                            Toutes les priorites
                        </option>

                        <option value="URGENTE">
                            URGENTE
                        </option>

                        <option value="NORMALE">
                            NORMALE
                        </option>

                        <option value="NON_URGENTE">
                            NON URGENTE
                        </option>

                    </select>

                </div>

            </div>

            <button
                    type="submit"
                    class="mt-4 bg-purple-600 text-white px-4 py-2 rounded"
            >
                Filtrer
            </button>

        </form>

    </div>


    <!-- DEMANDES EXPERTISE -->

    <h2 class="section-title">
        Mes demandes d'expertise
    </h2>

    <%
        List<DemandeExpertise> demandes =
                (List<DemandeExpertise>)
                        request.getAttribute("demandes");

        if (demandes != null && !demandes.isEmpty()) {

            for (DemandeExpertise demande : demandes) {
    %>

    <div class="bg-white shadow rounded p-5 mb-5">

        <p>
            <strong>Patient :</strong>

            <%= demande
                    .getConsultation()
                    .getPatient()
                    .getNom() %>

            <%= demande
                    .getConsultation()
                    .getPatient()
                    .getPrenom() %>
        </p>

        <p>
            <strong>Question :</strong>
            <%= demande.getQuestion() %>
        </p>

        <p>
            <strong>Donnees / analyses :</strong>
            <%= demande.getDonneesAnalyses() %>
        </p>

        <p>
            <strong>Priorite :</strong>
            <%= demande.getPriorite() %>
        </p>

        <p>
            <strong>Type :</strong>
            <%= demande.getTypeExpertise() %>
        </p>

        <p>
            <strong>Statut :</strong>
            <%= demande.getStatut() %>
        </p>

        <p>
            <strong>Date :</strong>
            <%= demande.getDateDemande() %>
        </p>


        <!-- DEMANDE EN ATTENTE -->

        <%
            if (demande.getStatut()
                    == Enums.StatutExpertise.EN_ATTENTE) {
        %>

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

        <%
            } else {
        %>


        <!-- DEMANDE TERMINEE -->

        <div class="mt-4 bg-green-100 p-3 rounded">

            <p>
                <strong>Avis :</strong>
                <%= demande.getAvisSpecialiste() %>
            </p>

            <p>
                <strong>Recommandations :</strong>
                <%= demande.getRecommandations() %>
            </p>

            <p>
                <strong>Date reponse :</strong>
                <%= demande.getDateReponse() %>
            </p>

        </div>

        <%
            }
        %>

    </div>

    <%
        }

    } else {
    %>

    <div class="bg-white p-5 rounded shadow mb-6">
        Aucune demande disponible.
    </div>

    <%
        }
    %>


    <!-- AJOUTER CRENEAU -->

    <div class="bg-white shadow rounded p-5 mt-8 mb-6">

        <h2 class="section-title">
            Ajouter un creneau
        </h2>

        <form
                action="${pageContext.request.contextPath}/specialiste"
                method="POST"
        >

            <input
                    type="hidden"
                    name="action"
                    value="ajouterCreneau"
            >

            <label class="block font-semibold mb-1">
                Date et heure de debut
            </label>

            <input
                    type="datetime-local"
                    name="dateHeureDebut"
                    required
                    class="w-full border rounded p-2 mb-3"
            >

            <p class="text-sm text-gray-500 mb-3">
                La duree du creneau est automatiquement de 30 minutes.
            </p>

            <button
                    type="submit"
                    class="bg-green-600 text-white px-4 py-2 rounded"
            >
                Ajouter le creneau
            </button>

        </form>

    </div>


    <!-- CRENEAUX -->

    <h2 class="section-title mt-8">
        Mes creneaux
    </h2>

    <%
        List<CreneauHoraire> creneaux =
                (List<CreneauHoraire>)
                        request.getAttribute("creneaux");

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
