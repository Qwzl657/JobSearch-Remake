<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="${.locale}">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Error — JobSearch</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</head>
<body class="bg-light d-flex align-items-center justify-content-center min-vh-100">
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6 text-center">
            <div class="card shadow-sm p-5">
                <h1 class="display-1 text-muted">${status!500}</h1>
                <#if status?? && status == 404>
                    <h4><@spring.message 'error.404.title'/></h4>
                <#else>
                    <h4><@spring.message 'error.500.title'/></h4>
                </#if>
                <a href="/vacancies" class="btn btn-primary mt-3">
                    <@spring.message 'error.back'/>
                </a>
            </div>
        </div>
    </div>
</div>
</body>
</html>