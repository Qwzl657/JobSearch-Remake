<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><@spring.message 'profile.edit.title'/></title>
</head>
<body>

<h2><@spring.message 'profile.edit.title'/></h2>

<form method="post" action="/profile/edit" enctype="multipart/form-data">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <label><@spring.message 'profile.name'/>:</label><br>
    <input type="text" name="name" value="${user.name!}" required/><br><br>

    <label><@spring.message 'profile.surname'/>:</label><br>
    <input type="text" name="surname" value="${user.surname!}" required/><br><br>

    <label><@spring.message 'profile.age'/>:</label><br>
    <input type="number" name="age" value="${user.age!}"/><br><br>

    <label><@spring.message 'profile.phone'/>:</label><br>
    <input type="text" name="phoneNumber" value="${user.phoneNumber!}"/><br><br>

    <label><@spring.message 'profile.avatar'/>:</label><br>
    <input type="file" name="avatar" accept="image/*"/><br><br>

    <button type="submit"><@spring.message 'profile.edit.submit'/></button>
    <a href="/profile"><@spring.message 'profile.edit.cancel'/></a>
</form>

</body>
</html>