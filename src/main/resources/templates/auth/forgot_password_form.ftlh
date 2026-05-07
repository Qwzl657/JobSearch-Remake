<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><@spring.message 'auth.forgot.title'/></title>
</head>
<body>

<h2><@spring.message 'auth.forgot.title'/></h2>

<#if message??>
    <p style="color:green">${message}</p>
</#if>
<#if error??>
    <p style="color:red">${error}</p>
</#if>

<form method="post" action="/auth/forgot-password">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <label><@spring.message 'auth.forgot.email'/>:</label><br>
    <input type="email" name="email" required/><br><br>
    <button type="submit"><@spring.message 'auth.forgot.submit'/></button>
</form>

<br>
<a href="/auth/login"><@spring.message 'auth.forgot.back'/></a>

</body>
</html>