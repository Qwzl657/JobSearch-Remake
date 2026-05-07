<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Забыли пароль?</title>
</head>
<body>

<h2>Восстановление пароля</h2>

<#if message??>
    <p style="color: green;">${message}</p>
</#if>

<#if error??>
    <p style="color: red;">${error}</p>
</#if>

<form method="post" action="/auth/forgot-password">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <label>Email:</label><br>
    <input type="email" name="email" required/><br><br>

    <button type="submit">Отправить ссылку</button>
</form>

<br>
<a href="/auth/login">Вернуться к входу</a>

</body>
</html>