<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>JobSearch</title>
</head>
<body>

<nav>
    <a href="/vacancies"><@spring.message 'nav.vacancies'/></a>
    <a href="/resumes/all"><@spring.message 'nav.resumes'/></a>

    <#if springMacroRequestContext.getUserPrincipal()??>
        <a href="/profile"><@spring.message 'nav.profile'/></a>
        <form method="post" action="/logout" style="display:inline">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button type="submit"><@spring.message 'nav.logout'/></button>
        </form>
    <#else>
        <a href="/auth/login"><@spring.message 'nav.login'/></a>
        <a href="/auth/register"><@spring.message 'nav.register'/></a>
    </#if>
    <span>
        <a href="?lang=ru"><@spring.message 'nav.language'/>: RU</a> |
        <a href="?lang=en">EN</a>
    </span>
</nav>

<#nested>

</body>
</html>