<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JobSearch</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="/">JobSearch</a>
        <div class="navbar-nav ms-auto">
            <a class="nav-link" href="/vacancies">Вакансии</a>
            <a class="nav-link" href="/resumes">Резюме</a>
            <a class="nav-link" href="/profile">Профиль</a>
            <a class="nav-link" href="/auth/login">Войти</a>
            <a class="nav-link" href="/auth/register">Регистрация</a>
        </div>
    </div>
</nav>

<div class="container mt-4">
    <#nested>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>