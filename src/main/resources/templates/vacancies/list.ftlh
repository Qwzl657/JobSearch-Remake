<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><@spring.message 'vacancy.title'/></title>
</head>
<body>

<h2><@spring.message 'vacancy.title'/></h2>

<#-- Сортировка -->
<form method="get" action="/vacancies">
    <select name="sort" onchange="this.form.submit()">
        <option value="date" <#if sort == "date">selected</#if>>
            <@spring.message 'vacancy.sort.date'/>
        </option>
        <option value="responses" <#if sort == "responses">selected</#if>>
            <@spring.message 'vacancy.sort.responses'/>
        </option>
    </select>
</form>

<#-- Кнопка создать (только для работодателя) -->
<#if springMacroRequestContext.getUserPrincipal()??>
    <a href="/vacancies/create"><@spring.message 'vacancy.create'/></a>
</#if>

<#-- Список вакансий -->
<#if vacancies?has_content>
    <#list vacancies as vacancy>
        <div>
            <h3>${vacancy.name}</h3>
            <p>${vacancy.description!}</p>
            <p><@spring.message 'vacancy.salary'/>: ${vacancy.salary!}</p>
            <p>
                <@spring.message 'vacancy.exp'/>:
                ${vacancy.expFrom!} <@spring.message 'vacancy.exp.from'/>
                — ${vacancy.expTo!} <@spring.message 'vacancy.exp.to'/>
            </p>
            <a href="/vacancies/${vacancy.id}/edit"><@spring.message 'vacancy.edit'/></a>
        </div>
        <hr>
    </#list>
<#else>
    <p><@spring.message 'vacancy.no.results'/></p>
</#if>

<#-- Пагинация -->
<div>
    <#if currentPage gt 0>
        <a href="/vacancies?page=${currentPage - 1}&sort=${sort}">
            <@spring.message 'page.prev'/>
        </a>
    </#if>

    <span>${currentPage + 1} / ${totalPages}</span>

    <#if currentPage lt totalPages - 1>
        <a href="/vacancies?page=${currentPage + 1}&sort=${sort}">
            <@spring.message 'page.next'/>
        </a>
    </#if>
</div>

</body>
</html>