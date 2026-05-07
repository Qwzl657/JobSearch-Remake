<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><@spring.message 'auth.login.title'/></title>
</head>
<body>

<h2><@spring.message 'auth.login.title'/></h2>

<#if param.error??>
    <p style="color:red"><@spring.message 'auth.login.error'/></p>
</#if>

<form method="post" action="/auth/login">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <label><@spring.message 'auth.login.email'/>:</label><br>
    <input type="email" name="username" required/><br><br>

    <label><@spring.message 'auth.login.password'/>:</label><br>
    <input type="password" name="password" required/><br><br>

    <button type="submit"><@spring.message 'auth.login.submit'/></button>
</form>

<br>
<a href="/auth/register"><@spring.message 'auth.login.register'/></a><br>
<a href="/auth/forgot-password"><@spring.message 'auth.login.forgot'/></a>

</body>
</html>