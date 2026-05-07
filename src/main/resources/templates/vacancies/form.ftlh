<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>
        <#if vacancyDto.id??>
            <@spring.message 'vacancy.form.title.edit'/>
        <#else>
            <@spring.message 'vacancy.form.title.create'/>
        </#if>
    </title>
</head>
<body>

<h2>
    <#if vacancyDto.id??>
        <@spring.message 'vacancy.form.title.edit'/>
    <#else>
        <@spring.message 'vacancy.form.title.create'/>
    </#if>
</h2>

<form method="post"
      action="<#if vacancyDto.id??>/vacancies/${vacancyDto.id}/edit<#else>/vacancies/create</#if>">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <label><@spring.message 'vacancy.name'/>:</label><br>
    <input type="text" name="name" value="${vacancyDto.name!}" required/><br><br>

    <label><@spring.message 'vacancy.description'/>:</label><br>
    <textarea name="description">${vacancyDto.description!}</textarea><br><br>

    <label><@spring.message 'vacancy.salary'/>:</label><br>
    <input type="number" name="salary" value="${vacancyDto.salary!}"/><br><br>

    <label><@spring.message 'vacancy.exp.from'/>:</label><br>
    <input type="number" name="expFrom" value="${vacancyDto.expFrom!}"/><br><br>

    <label><@spring.message 'vacancy.exp.to'/>:</label><br>
    <input type="number" name="expTo" value="${vacancyDto.expTo!}"/><br><br>

    <label>
        <input type="checkbox" name="active"
               <#if vacancyDto.active?? && vacancyDto.active>checked</#if>/>
        <@spring.message 'vacancy.active'/>
    </label><br><br>

    <button type="submit"><@spring.message 'vacancy.form.submit'/></button>
</form>

</body>
</html>