<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Сброс пароля</title>
</head>
<body>

<h2>Новый пароль</h2>

<#if message??>
    <p style="color: green;">${message}</p>
</#if>

<#if error??>
    <p style="color: red;">${error}</p>
</#if>

<#if token??>
    <form method="post" action="/auth/reset-password">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" name="token" value="${token}"/>

        <label>Новый пароль:</label><br>
        <input type="password" name="password" required minlength="8"/><br><br>

        <button type="submit">Изменить пароль</button>
    </form>
</#if>

<br>
<a href="/auth/login">Вернуться к входу</a>

</body>
</html>