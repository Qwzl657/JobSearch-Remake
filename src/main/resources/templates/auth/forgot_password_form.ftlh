<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><@spring.message 'auth.forgot.title'/> — JobSearch</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</head>
<body class="bg-light">
<div class="container">
    <div class="row justify-content-center mt-5">
        <div class="col-md-5">
            <div class="card shadow-sm">
                <div class="card-body p-4">
                    <h4 class="card-title text-center mb-4">
                        <@spring.message 'auth.forgot.title'/>
                    </h4>

                    <#if message??>
                        <div class="alert alert-success">${message}</div>
                    </#if>
                    <#if error??>
                        <div class="alert alert-danger">${error}</div>
                    </#if>

                    <form method="post" action="/auth/forgot-password">
                        <#if _csrf??>
                            <input type="hidden"
                                   name="${(_csrf.parameterName)!'csrf-param-name'}"
                                   value="${(_csrf.token)!'csrf-token'}"/>
                        </#if>
                        <div class="mb-3">
                            <label class="form-label"><@spring.message 'auth.forgot.email'/></label>
                            <input type="email" name="email" class="form-control" required/>
                        </div>
                        <div class="d-grid mb-3">
                            <button type="submit" class="btn btn-primary">
                                <@spring.message 'auth.forgot.submit'/>
                            </button>
                        </div>
                    </form>

                    <div class="text-center">
                        <a href="/auth/login"><@spring.message 'auth.forgot.back'/></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>