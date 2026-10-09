<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Consultation" %>

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>Espace Generaliste</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-100 min-h-screen">

<div class="max-w-6xl mx-auto p-6">

    <div class="flex justify-between items-center mb-6">
        <h1 class="text-3xl font-bold text-blue-700">
            Espace Generaliste
        </h1>

        <a
            href="${pageContext.request.contextPath}/auth"
            class="bg-red-500 text-white px-4 py-2 rounded"
        >
            Logout
        </a>
    </div>

    <h2 class="text-xl font-bold mb-4">
        File d'attente
    </h2>

    <%
        List<Consultation> fileAttente =
                (List<Consultation>) request.getAttribute("fileAttente");

        if (fileAttente != null && !fileAttente.isEmpty()) {

            for (Consultation consultation : fileAttente) {
    %>

    <div class="bg-white shadow rounded p-5 mb-5">

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

        <hr class="my-4">

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

        <hr class="my-5">

        <form
            action="${pageContext.request.contextPath}/generaliste"
            method="POST"
            class="mb-4"
        >

            <input
                type="hidden"
                name="action"
                value="cloturer"
            >

            <input
                type="hidden"
                name="consultationId"
                value="<%= consultation.getId() %>"
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
                value="<%= consultation.getId() %>"
            >

            <input
                type="number"
                name="specialisteId"
                placeholder="ID specialiste"
                required
                class="w-full border px-3 py-2 rounded mb-2"
            >

            <input
                type="number"
                name="creneauId"
                placeholder="ID creneau"
                required
                class="w-full border px-3 py-2 rounded mb-2"
            >

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
                <option value="URGENTE">URGENTE</option>
                <option value="NORMALE">NORMALE</option>
                <option value="NON_URGENTE">NON URGENTE</option>
            </select>

            <select
                name="typeExpertise"
                required
                class="w-full border px-3 py-2 rounded mb-3"
            >
                <option value="SYNCHRONE">SYNCHRONE</option>
                <option value="ASYNCHRONE">ASYNCHRONE</option>
            </select>

            <button
                type="submit"
                class="bg-purple-600 text-white px-4 py-2 rounded"
            >
                Demander expertise
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