<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>Connexion - Tele Expertise</title>

    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css">
</head>

<body class="login-body">

<div class="login-card p-8">

    <h2 class="text-3xl font-extrabold text-center text-slate-900 mb-2">
        Tele Expertise
    </h2>

    <p class="text-center text-slate-500 mb-6">
        Connexion securisee
    </p>

    <% if (request.getParameter("error") != null) { %>

        <div class="app-alert text-center">

            <% if ("invalid".equals(request.getParameter("error"))) { %>

                Email ou mot de passe incorrect.

            <% } else if ("db".equals(request.getParameter("error"))) { %>

                Probleme de connexion a la base de donnees.

            <% } else { %>

                Une erreur est survenue.

            <% } %>

        </div>

    <% } %>

    <form
        action="${pageContext.request.contextPath}/auth"
        method="POST"
    >

        <div class="mb-4">

            <label class="block mb-2 font-semibold">
                Email
            </label>

            <input
                type="email"
                name="email"
                required
                class="w-full border px-3 py-2 rounded"
            >

        </div>

        <div class="mb-6">

            <label class="block mb-2 font-semibold">
                Mot de passe
            </label>

            <input
                type="password"
                name="motDePasse"
                required
                class="w-full border px-3 py-2 rounded"
            >

        </div>

        <button
            type="submit"
            class="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700"
        >
            Se connecter
        </button>

    </form>

</div>

</body>
</html>
