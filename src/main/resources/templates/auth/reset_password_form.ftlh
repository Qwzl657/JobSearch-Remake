<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><@spring.message 'auth.reset.title'/></title>
</head>
<body>

<h2><@spring.message 'auth.reset.title'/></h2>

<#if message??>
    <p style="color:green">${message}</p>
</#if>
<#if error??>
    <p style="color:red">${error}</p>
</#if>

<#if token??>
    <form method="post" action="/auth/reset-password">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" name="token" value="${token}"/>
        <label><@spring.message 'auth.reset.password'/>:</label><br>
        <input type="password" name="password" required minlength="8"/><br><br>
        <button type="submit"><@spring.message 'auth.reset.submit'/></button>
    </form>
</#if>

<br>
<a href="/auth/login"><@spring.message 'auth.forgot.back'/></a>

</body>
</html>