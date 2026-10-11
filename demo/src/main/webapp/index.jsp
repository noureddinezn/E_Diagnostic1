<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>EBghitek</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css">
</head>

<body class="landing-body">

<main class="landing-card p-8 text-center">

    <p class="text-sm font-bold uppercase tracking-wider text-blue-600 mb-3">
        Plateforme medicale
    </p>

    <h1 class="text-4xl font-extrabold text-slate-900 mb-3">
        E-Diagnostic
    </h1>

    <p class="text-slate-600 mb-6">
        Plateforme de consultation medicale a distance.
    </p>

    <a
            href="${pageContext.request.contextPath}/login.jsp"
            class="landing-link bg-blue-600 text-white px-5 py-2 hover:bg-blue-700"
    >
        Se connecter
    </a>

</main>

</body>
</html>
