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

            <#if Session.SPRING_SECURITY_CONTEXT??>
                <#assign auth = Session.SPRING_SECURITY_CONTEXT.authentication>

                <#if auth.authorities?seq_contains("ROLE_EMPLOYER")>
                    <a class="nav-link" href="/resumes/all">Резюме соискателей</a>
                </#if>

                <#if auth.authorities?seq_contains("ROLE_APPLICANT")>
                    <a class="nav-link" href="/resumes/all">Все резюме</a>
                </#if>

                <a class="nav-link" href="/profile">Профиль</a>

                <#if _csrf??>
                    <form action="/logout" method="post" class="d-inline">
                        <input type="hidden"
                               name="${(_csrf.parameterName)!'csrf'}"
                               value="${(_csrf.token)!''}"/>
                        <button type="submit" class="btn btn-link nav-link" style="display: inline; padding: 0.5rem; border: none; vertical-align: baseline;">Выйти</button>
                    </form>
                <#else>
                    <a class="nav-link" href="/logout">Выйти</a>
                </#if>

            <#else>
                <a class="nav-link" href="/auth/login">Войти</a>
                <a class="nav-link" href="/auth/register">Регистрация</a>
            </#if>
        </div>
    </div>
</nav>

<div class="container mt-4">
    <#nested>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>