<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Patient" %>
<%@ page import="com.example.model.Consultation" %>

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>Espace Infirmier</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css">
</head>

<body class="app-body">

<div class="app-shell">

    <div class="topbar">

        <h1 class="page-title">
            Espace Infirmier
        </h1>

        <a
                href="${pageContext.request.contextPath}/auth"
                class="bg-red-500 text-white px-4 py-2 rounded"
        >
            Logout
        </a>

    </div>


    <!-- ERRORS -->

    <%
        String error = request.getParameter("error");

        if (error != null) {
    %>

    <div class="app-alert">

        <%
            if ("patient-not-found".equals(error)) {
        %>

        Patient introuvable.

        <%
        } else if ("invalid-data".equals(error)) {
        %>

        Donnees invalides. Verifiez les informations saisies.

        <%
        } else {
        %>

        Une erreur est survenue.

        <%
            }
        %>

    </div>

    <%
        }
    %>


    <!-- RECHERCHE PATIENT -->

    <div class="bg-white p-5 rounded shadow mb-6">

        <h2 class="section-title">
            Rechercher un patient
        </h2>

        <form
                action="${pageContext.request.contextPath}/infirmier"
                method="POST"
        >

            <input
                    type="hidden"
                    name="action"
                    value="rechercher"
            >

            <input
                    type="text"
                    name="numeroSocial"
                    placeholder="Numero securite sociale"
                    required
                    class="w-full border px-3 py-2 rounded mb-3"
            >

            <button
                    type="submit"
                    class="bg-blue-600 text-white px-4 py-2 rounded"
            >
                Rechercher
            </button>

        </form>

    </div>


    <%
        Patient patientTrouve =
                (Patient) request.getAttribute("patientTrouve");
    %>


    <% if (patientTrouve != null) { %>


    <!-- PATIENT EXISTANT -->

    <div class="bg-white p-5 rounded shadow mb-6">

        <h2 class="section-title">
            Patient trouve
        </h2>

        <p>
            <strong>Nom :</strong>
            <%= patientTrouve.getNom() %>
        </p>

        <p>
            <strong>Prenom :</strong>
            <%= patientTrouve.getPrenom() %>
        </p>

        <p>
            <strong>Numero social :</strong>
            <%= patientTrouve.getNumeroSocial() %>
        </p>


        <form
                action="${pageContext.request.contextPath}/infirmier"
                method="POST"
                class="mt-4"
        >

            <input
                    type="hidden"
                    name="action"
                    value="patientExistant"
            >

            <input
                    type="hidden"
                    name="numeroSocial"
                    value="<%= patientTrouve.getNumeroSocial() %>"
            >


            <input
                    type="text"
                    name="tension"
                    placeholder="Tension arterielle"
                    required
                    class="w-full border px-3 py-2 rounded mb-2"
            >

            <input
                    type="number"
                    name="frequenceCardiaque"
                    placeholder="Frequence cardiaque"
                    required
                    class="w-full border px-3 py-2 rounded mb-2"
            >

            <input
                    type="number"
                    step="0.1"
                    name="temperature"
                    placeholder="Temperature"
                    required
                    class="w-full border px-3 py-2 rounded mb-2"
            >

            <input
                    type="number"
                    name="frequenceRespiratoire"
                    placeholder="Frequence respiratoire"
                    required
                    class="w-full border px-3 py-2 rounded mb-2"
            >

            <input
                    type="number"
                    step="0.1"
                    name="poids"
                    placeholder="Poids"
                    class="w-full border px-3 py-2 rounded mb-2"
            >

            <input
                    type="number"
                    step="0.1"
                    name="taille"
                    placeholder="Taille"
                    class="w-full border px-3 py-2 rounded mb-3"
            >


            <button
                    type="submit"
                    class="bg-green-600 text-white px-4 py-2 rounded"
            >
                Ajouter a la file d'attente
            </button>

        </form>

    </div>

    <% } %>


    <!-- NOUVEAU PATIENT -->

    <div class="bg-white p-5 rounded shadow mb-6">

        <h2 class="section-title">
            Nouveau patient
        </h2>


        <form
                action="${pageContext.request.contextPath}/infirmier"
                method="POST"
        >

            <input
                    type="hidden"
                    name="action"
                    value="nouveauPatient"
            >


            <input
                    type="text"
                    name="nom"
                    placeholder="Nom"
                    required
                    class="w-full border px-3 py-2 rounded mb-2"
            >

            <input
                    type="text"
                    name="prenom"
                    placeholder="Prenom"
                    required
                    class="w-full border px-3 py-2 rounded mb-2"
            >

            <input
                    type="date"
                    name="dateNaissance"
                    required
                    class="w-full border px-3 py-2 rounded mb-2"
            >

            <input
                    type="text"
                    name="numeroSocial"
                    placeholder="Numero securite sociale"
                    required
                    class="w-full border px-3 py-2 rounded mb-2"
            >

            <input
                    type="text"
                    name="telephone"
                    placeholder="Telephone"
                    class="w-full border px-3 py-2 rounded mb-2"
            >

            <input
                    type="text"
                    name="adresse"
                    placeholder="Adresse"
                    class="w-full border px-3 py-2 rounded mb-2"
            >

            <input
                    type="text"
                    name="mutuelle"
                    placeholder="Mutuelle"
                    class="w-full border px-3 py-2 rounded mb-2"
            >


            <textarea
                    name="antecedents"
                    placeholder="Antecedents"
                    class="w-full border px-3 py-2 rounded mb-2"
            ></textarea>

            <textarea
                    name="allergies"
                    placeholder="Allergies"
                    class="w-full border px-3 py-2 rounded mb-2"
            ></textarea>

            <textarea
                    name="traitementsEnCours"
                    placeholder="Traitements en cours"
                    class="w-full border px-3 py-2 rounded mb-4"
            ></textarea>


            <h3 class="font-bold mb-3">
                Signes vitaux
            </h3>


            <input
                    type="text"
                    name="tension"
                    placeholder="Tension arterielle"
                    required
                    class="w-full border px-3 py-2 rounded mb-2"
            >

            <input
                    type="number"
                    name="frequenceCardiaque"
                    placeholder="Frequence cardiaque"
                    required
                    class="w-full border px-3 py-2 rounded mb-2"
            >

            <input
                    type="number"
                    step="0.1"
                    name="temperature"
                    placeholder="Temperature"
                    required
                    class="w-full border px-3 py-2 rounded mb-2"
            >

            <input
                    type="number"
                    name="frequenceRespiratoire"
                    placeholder="Frequence respiratoire"
                    required
                    class="w-full border px-3 py-2 rounded mb-2"
            >

            <input
                    type="number"
                    step="0.1"
                    name="poids"
                    placeholder="Poids"
                    class="w-full border px-3 py-2 rounded mb-2"
            >

            <input
                    type="number"
                    step="0.1"
                    name="taille"
                    placeholder="Taille"
                    class="w-full border px-3 py-2 rounded mb-4"
            >


            <button
                    type="submit"
                    class="bg-blue-600 text-white px-4 py-2 rounded"
            >
                Enregistrer patient
            </button>

        </form>

    </div>


    <!-- PATIENTS DU JOUR -->

    <div class="bg-white p-5 rounded shadow">

        <h2 class="section-title">
            Patients du jour
        </h2>

        <%
            List<Consultation> patientsDuJour =
                    (List<Consultation>) request.getAttribute("patientsDuJour");

            if (patientsDuJour != null && !patientsDuJour.isEmpty()) {

                for (Consultation consultation : patientsDuJour) {
        %>


        <div class="border-b py-3">

            <p>
                <strong>Patient :</strong>
                <%= consultation.getPatient().getNom() %>
                <%= consultation.getPatient().getPrenom() %>
            </p>

            <p>
                <strong>Numero social :</strong>
                <%= consultation.getPatient().getNumeroSocial() %>
            </p>

            <p>
                <strong>Heure arrivee :</strong>
                <%= consultation.getDateCreation() %>
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
                <strong>Statut :</strong>
                <%= consultation.getStatut() %>
            </p>

        </div>


        <%
            }

        } else {
        %>

        <p>
            Aucun patient enregistre aujourd'hui.
        </p>

        <%
            }
        %>

    </div>

</div>

</body>
</html>
