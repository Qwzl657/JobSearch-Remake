<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>
        <#if resumeDto.id??>
            <@spring.message 'resume.form.title.edit'/>
        <#else>
            <@spring.message 'resume.form.title.create'/>
        </#if>
    </title>
</head>
<body>

<h2>
    <#if resumeDto.id??>
        <@spring.message 'resume.form.title.edit'/>
    <#else>
        <@spring.message 'resume.form.title.create'/>
    </#if>
</h2>

<form method="post"
      action="<#if resumeDto.id??>/resumes/${resumeDto.id}/edit<#else>/resumes/create</#if>">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <label><@spring.message 'resume.name'/>:</label><br>
    <input type="text" name="name" value="${resumeDto.name!}" required/><br><br>

    <label><@spring.message 'resume.salary'/>:</label><br>
    <input type="number" name="salary" value="${resumeDto.salary!}"/><br><br>

    <label>
        <input type="checkbox" name="active"
               <#if resumeDto.active?? && resumeDto.active>checked</#if>/>
        <@spring.message 'resume.active'/>
    </label><br><br>

    <button type="submit"><@spring.message 'resume.form.submit'/></button>
</form>

</body>
</html>