<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><@spring.message 'auth.register.title'/></title>
</head>
<body>

<h2><@spring.message 'auth.register.title'/></h2>

<form method="post" action="/auth/register">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <label><@spring.message 'auth.register.name'/>:</label><br>
    <input type="text" name="name" value="${userCreateDto.name!}" required/><br>
    <#if userCreateDto.name?? && userCreateDto.name == "">
        <span style="color:red"><@spring.message 'validation.name.blank'/></span>
    </#if>
    <br>

    <label><@spring.message 'auth.register.surname'/>:</label><br>
    <input type="text" name="surname" value="${userCreateDto.surname!}" required/><br><br>

    <label><@spring.message 'auth.register.age'/>:</label><br>
    <input type="number" name="age" value="${userCreateDto.age!}" min="14" max="100"/><br><br>

    <label><@spring.message 'auth.register.email'/>:</label><br>
    <input type="email" name="email" value="${userCreateDto.email!}" required/><br><br>

    <label><@spring.message 'auth.register.phone'/>:</label><br>
    <input type="text" name="phoneNumber" value="${userCreateDto.phoneNumber!}" required/><br><br>

    <label><@spring.message 'auth.register.type'/>:</label><br>
    <select name="accountType">
        <option value="APPLICANT"><@spring.message 'auth.register.type.applicant'/></option>
        <option value="EMPLOYER"><@spring.message 'auth.register.type.employer'/></option>
    </select><br><br>

    <label><@spring.message 'auth.register.password'/>:</label><br>
    <input type="password" name="password" required minlength="8"/><br><br>

    <button type="submit"><@spring.message 'auth.register.submit'/></button>
</form>

<br>
<a href="/auth/login"><@spring.message 'auth.register.have.account'/></a>

</body>
</html>