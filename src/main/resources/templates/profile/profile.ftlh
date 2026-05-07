<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><@spring.message 'profile.title'/></title>
</head>
<body>

<h2><@spring.message 'profile.title'/></h2>

<#if user??>
    <p><@spring.message 'profile.name'/>: ${user.name}</p>
    <p><@spring.message 'profile.surname'/>: ${user.surname}</p>
    <p><@spring.message 'profile.age'/>: ${user.age!}</p>
    <p><@spring.message 'profile.email'/>: ${user.email}</p>
    <p><@spring.message 'profile.phone'/>: ${user.phoneNumber!}</p>
    <p>
        <@spring.message 'profile.avatar'/>:
        <img src="/avatars/${user.avatar!}" width="100" alt="avatar"/>
    </p>
    <p>
        <#if user.accountType == "APPLICANT">
            <@spring.message 'profile.type.applicant'/>
        <#else>
            <@spring.message 'profile.type.employer'/>
        </#if>
    </p>
    <a href="/profile/edit"><@spring.message 'profile.edit'/></a>
</#if>

<hr>

<#-- Резюме для соискателя -->
<#if user.accountType == "APPLICANT">
    <h3><@spring.message 'resume.title'/></h3>
    <a href="/resumes/create"><@spring.message 'resume.create'/></a>
    <#if resumes?has_content>
        <#list resumes as resume>
            <div>
                <p>${resume.name}</p>
                <a href="/resumes/${resume.id}/edit"><@spring.message 'resume.edit'/></a>
            </div>
        </#list>
    <#else>
        <p><@spring.message 'resume.no.results'/></p>
    </#if>

<#-- Пагинация -->
    <#if currentPage gt 0>
        <a href="/profile?page=${currentPage - 1}"><@spring.message 'page.prev'/></a>
    </#if>
    <span>${currentPage + 1} / ${totalPages}</span>
    <#if currentPage lt totalPages - 1>
        <a href="/profile?page=${currentPage + 1}"><@spring.message 'page.next'/></a>
    </#if>
</#if>

<#-- Вакансии для работодателя -->
<#if user.accountType == "EMPLOYER">
    <h3><@spring.message 'vacancy.title'/></h3>
    <a href="/vacancies/create"><@spring.message 'vacancy.create'/></a>
    <#if vacancies?has_content>
        <#list vacancies as vacancy>
            <div>
                <p>${vacancy.name}</p>
                <a href="/vacancies/${vacancy.id}/edit"><@spring.message 'vacancy.edit'/></a>
            </div>
        </#list>
    <#else>
        <p><@spring.message 'vacancy.no.results'/></p>
    </#if>

<#-- Пагинация -->
    <#if currentPage gt 0>
        <a href="/profile?page=${currentPage - 1}"><@spring.message 'page.prev'/></a>
    </#if>
    <span>${currentPage + 1} / ${totalPages}</span>
    <#if currentPage lt totalPages - 1>
        <a href="/profile?page=${currentPage + 1}"><@spring.message 'page.next'/></a>
    </#if>
</#if>

</body>
</html>