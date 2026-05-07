<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><@spring.message 'resume.title'/></title>
</head>
<body>

<h2><@spring.message 'resume.title'/></h2>

<#-- Кнопка создать (только для соискателя) -->
<#if springMacroRequestContext.getUserPrincipal()??>
    <a href="/resumes/create"><@spring.message 'resume.create'/></a>
</#if>

<#-- Список резюме -->
<#if resumes?has_content>
    <#list resumes as resume>
        <div>
            <h3>${resume.name}</h3>
            <p><@spring.message 'resume.salary'/>: ${resume.salary!}</p>
            <p>
                <@spring.message 'resume.active'/>:
                <#if resume.active>✓<#else>✗</#if>
            </p>
            <a href="/resumes/${resume.id}/edit"><@spring.message 'resume.edit'/></a>
        </div>
        <hr>
    </#list>
<#else>
    <p><@spring.message 'resume.no.results'/></p>
</#if>

<#-- Пагинация -->
<div>
    <#if currentPage gt 0>
        <a href="/resumes/all?page=${currentPage - 1}">
            <@spring.message 'page.prev'/>
        </a>
    </#if>

    <span>${currentPage + 1} / ${totalPages}</span>

    <#if currentPage lt totalPages - 1>
        <a href="/resumes/all?page=${currentPage + 1}">
            <@spring.message 'page.next'/>
        </a>
    </#if>
</div>

</body>
</html>