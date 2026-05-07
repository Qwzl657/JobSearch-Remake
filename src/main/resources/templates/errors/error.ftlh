<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>
        <#if status?? && status == 404>
            <@spring.message 'error.404.title'/>
        <#else>
            <@spring.message 'error.500.title'/>
        </#if>
    </title>
</head>
<body>

<h2>
    <#if status?? && status == 404>
        <@spring.message 'error.404.title'/>
    <#else>
        <@spring.message 'error.500.title'/>
    </#if>
</h2>

<#if reason??>
    <p>${reason}</p>
</#if>

<a href="/vacancies"><@spring.message 'error.back'/></a>

</body>
</html>