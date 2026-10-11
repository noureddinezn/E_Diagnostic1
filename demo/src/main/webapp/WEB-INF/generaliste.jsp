<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>

<%@ page import="com.example.model.Consultation" %>
<%@ page import="com.example.model.Utilisateur" %>
<%@ page import="com.example.model.CreneauHoraire" %>
<%@ page import="com.example.model.Enums" %>

<%
    if (request.getAttribute("fileAttente") == null) {
        response.sendRedirect(
                request.getContextPath() + "/generaliste"
        );
        return;
    }
%>

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">

    <title>Espace Generaliste</title>

    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css">
</head>

<body class="app-body">

<div class="app-shell">


    <!-- HEADER -->

    <div class="topbar">

        <h1 class="page-title">
            Espace Generaliste
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

        Donnees invalides ou une erreur est survenue.

    </div>

    <%
        }
    %>


    <!-- CONSULTATION ACTIVE -->

    <%
        Consultation consultationActive =
                (Consultation) request.getAttribute(
                        "consultationActive"
                );

        if (consultationActive != null) {
    %>


    <div class="bg-white shadow rounded p-5 mb-6">

        <h2 class="section-title text-green-700">
            Consultation en cours
        </h2>


        <p>
            <strong>Patient :</strong>

            <%= consultationActive.getPatient().getNom() %>

            <%= consultationActive.getPatient().getPrenom() %>
        </p>


        <p>
            <strong>Numero social :</strong>

            <%= consultationActive
                    .getPatient()
                    .getNumeroSocial() %>
        </p>


        <p>
            <strong>Motif :</strong>

            <%= consultationActive.getMotif() %>
        </p>


        <p>
            <strong>Symptomes :</strong>

            <%= consultationActive.getSymptomes() %>
        </p>


        <p>
            <strong>Statut :</strong>

            <%= consultationActive.getStatut() %>
        </p>


        <hr class="my-5">


        <!-- CLOTURER DIRECTEMENT -->

        <h3 class="text-lg font-bold mb-3">
            Cloturer directement
        </h3>


        <form
                action="${pageContext.request.contextPath}/generaliste"
                method="POST"
                class="mb-6"
        >

            <input
                    type="hidden"
                    name="action"
                    value="cloturer"
            >


            <input
                    type="hidden"
                    name="consultationId"
                    value="<%= consultationActive.getId() %>"
            >


            <textarea
                    name="diagnostic"
                    placeholder="Diagnostic"
                    required
                    class="w-full border px-3 py-2 rounded mb-2"
            ></textarea>


            <textarea
                    name="medicaments"
                    placeholder="Medicaments"
                    class="w-full border px-3 py-2 rounded mb-3"
            ></textarea>


            <button
                    type="submit"
                    class="bg-green-600 text-white px-4 py-2 rounded"
            >
                Cloturer consultation
            </button>

        </form>


        <hr class="my-5">


        <!-- CHOISIR SPECIALITE -->

        <h3 class="text-lg font-bold mb-3">
            Demander expertise
        </h3>


        <form
                action="${pageContext.request.contextPath}/generaliste"
                method="GET"
                class="mb-4"
        >

            <input
                    type="hidden"
                    name="consultationId"
                    value="<%= consultationActive.getId() %>"
            >


            <select
                    name="specialite"
                    required
                    class="w-full border px-3 py-2 rounded mb-3"
            >

                <option value="">
                    Choisir une specialite
                </option>

                <%
                    for (Enums.Specialite specialite
                            : Enums.Specialite.values()) {
                %>

                <option value="<%= specialite.name() %>">

                    <%= specialite.name() %>

                </option>

                <%
                    }
                %>

            </select>


            <button
                    type="submit"
                    class="bg-purple-600 text-white px-4 py-2 rounded"
            >
                Chercher specialistes
            </button>

        </form>


        <!-- LISTE SPECIALISTES -->

        <%
            List<Utilisateur> specialistes =
                    (List<Utilisateur>)
                            request.getAttribute(
                                    "specialistes"
                            );

            Enums.Specialite specialiteSelectionnee =
                    (Enums.Specialite)
                            request.getAttribute(
                                    "specialiteSelectionnee"
                            );

            if (specialistes != null) {
        %>


        <div class="mt-5">

            <h3 class="font-bold mb-3">
                Specialistes disponibles
            </h3>


            <% if (!specialistes.isEmpty()) { %>


            <form
                    action="${pageContext.request.contextPath}/generaliste"
                    method="GET"
            >

                <input
                        type="hidden"
                        name="consultationId"
                        value="<%= consultationActive.getId() %>"
                >


                <input
                        type="hidden"
                        name="specialite"
                        value="<%= specialiteSelectionnee %>"
                >


                <select
                        name="specialisteId"
                        required
                        class="w-full border px-3 py-2 rounded mb-3"
                >

                    <option value="">
                        Choisir un specialiste
                    </option>


                    <%
                        for (Utilisateur specialiste : specialistes) {
                    %>

                    <option value="<%= specialiste.getId() %>">

                        Dr
                        <%= specialiste.getNom() %>
                        <%= specialiste.getPrenom() %>

                        -

                        <%= specialiste.getSpecialite() %>

                        -

                        <%= specialiste.getTarifConsultation() %> DH

                    </option>

                    <%
                        }
                    %>

                </select>


                <button
                        type="submit"
                        class="bg-indigo-600 text-white px-4 py-2 rounded"
                >
                    Voir les creneaux
                </button>

            </form>


            <% } else { %>

            <p class="text-gray-500">
                Aucun specialiste trouve.
            </p>

            <% } %>

        </div>


        <%
            }
        %>


        <!-- LISTE CRENEAUX -->

        <%
            List<CreneauHoraire> creneaux =
                    (List<CreneauHoraire>)
                            request.getAttribute(
                                    "creneaux"
                            );

            Long specialisteIdSelectionne =
                    (Long)
                            request.getAttribute(
                                    "specialisteIdSelectionne"
                            );

            if (creneaux != null) {
        %>


        <div class="mt-6">

            <h3 class="font-bold mb-3">
                Creneaux disponibles
            </h3>


            <% if (!creneaux.isEmpty()) { %>


            <!-- DEMANDE EXPERTISE -->

            <form
                    action="${pageContext.request.contextPath}/generaliste"
                    method="POST"
            >

                <input
                        type="hidden"
                        name="action"
                        value="demanderExpertise"
                >


                <input
                        type="hidden"
                        name="consultationId"
                        value="<%= consultationActive.getId() %>"
                >


                <input
                        type="hidden"
                        name="specialisteId"
                        value="<%= specialisteIdSelectionne %>"
                >


                <select
                        name="creneauId"
                        required
                        class="w-full border px-3 py-2 rounded mb-3"
                >

                    <option value="">
                        Choisir un creneau
                    </option>


                    <%
                        for (CreneauHoraire creneau
                                : creneaux) {
                    %>

                    <option value="<%= creneau.getId() %>">

                        <%= creneau.getDateHeureDebut() %>

                        -

                        <%= creneau.getDateHeureFin() %>

                    </option>

                    <%
                        }
                    %>

                </select>


                <textarea
                        name="question"
                        placeholder="Question au specialiste"
                        required
                        class="w-full border px-3 py-2 rounded mb-2"
                ></textarea>


                <textarea
                        name="donneesAnalyses"
                        placeholder="Donnees et analyses"
                        class="w-full border px-3 py-2 rounded mb-2"
                ></textarea>


                <select
                        name="priorite"
                        required
                        class="w-full border px-3 py-2 rounded mb-2"
                >

                    <option value="URGENTE">
                        URGENTE
                    </option>

                    <option value="NORMALE" selected>
                        NORMALE
                    </option>

                    <option value="NON_URGENTE">
                        NON URGENTE
                    </option>

                </select>


                <select
                        name="typeExpertise"
                        required
                        class="w-full border px-3 py-2 rounded mb-3"
                >

                    <option value="SYNCHRONE">
                        SYNCHRONE
                    </option>

                    <option value="ASYNCHRONE">
                        ASYNCHRONE
                    </option>

                </select>


                <button
                        type="submit"
                        class="bg-purple-700 text-white px-4 py-2 rounded"
                >
                    Envoyer demande expertise
                </button>

            </form>


            <% } else { %>

            <p class="text-gray-500">
                Aucun creneau disponible pour ce specialiste.
            </p>

            <% } %>

        </div>


        <%
            }
        %>


    </div>


    <%
        }
    %>


    <!-- FILE ATTENTE -->

    <h2 class="section-title">
        File d'attente
    </h2>


    <%
        List<Consultation> fileAttente =
                (List<Consultation>)
                        request.getAttribute(
                                "fileAttente"
                        );


        if (fileAttente != null
                && !fileAttente.isEmpty()) {


            for (Consultation consultation
                    : fileAttente) {
    %>


    <div class="bg-white shadow rounded p-5 mb-5">


        <p>
            <strong>Patient :</strong>

            <%= consultation.getPatient().getNom() %>

            <%= consultation.getPatient().getPrenom() %>
        </p>


        <p>
            <strong>Numero social :</strong>

            <%= consultation
                    .getPatient()
                    .getNumeroSocial() %>
        </p>


        <p>
            <strong>Temperature :</strong>

            <%= consultation.getTemperature() %>
        </p>


        <p>
            <strong>Tension :</strong>

            <%= consultation.getTensionArterielle() %>
        </p>


        <p>
            <strong>Frequence cardiaque :</strong>

            <%= consultation.getFrequenceCardiaque() %>
        </p>


        <p>
            <strong>Frequence respiratoire :</strong>

            <%= consultation.getFrequenceRespiratoire() %>
        </p>


        <p>
            <strong>Statut :</strong>

            <%= consultation.getStatut() %>
        </p>


        <hr class="my-4">


        <!-- COMMENCER CONSULTATION -->

        <form
                action="${pageContext.request.contextPath}/generaliste"
                method="POST"
        >

            <input
                    type="hidden"
                    name="action"
                    value="commencer"
            >


            <input
                    type="hidden"
                    name="consultationId"
                    value="<%= consultation.getId() %>"
            >


            <input
                    type="text"
                    name="motif"
                    placeholder="Motif"
                    required
                    class="w-full border px-3 py-2 rounded mb-2"
            >


            <textarea
                    name="observations"
                    placeholder="Observations"
                    class="w-full border px-3 py-2 rounded mb-2"
            ></textarea>


            <textarea
                    name="symptomes"
                    placeholder="Symptomes"
                    class="w-full border px-3 py-2 rounded mb-2"
            ></textarea>


            <textarea
                    name="examenClinique"
                    placeholder="Examen clinique"
                    class="w-full border px-3 py-2 rounded mb-3"
            ></textarea>


            <button
                    type="submit"
                    class="bg-blue-600 text-white px-4 py-2 rounded"
            >
                Commencer consultation
            </button>

        </form>

    </div>


    <%
        }

    } else {
    %>


    <div class="bg-white p-5 rounded shadow">

        Aucun patient dans la file d'attente.

    </div>


    <%
        }
    %>


</div>

</body>
</html>
