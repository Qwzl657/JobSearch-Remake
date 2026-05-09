<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><@spring.message 'auth.reset.title'/> — JobSearch</title>
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
                        <@spring.message 'auth.reset.title'/>
                    </h4>

                    <#if message??>
                        <div class="alert alert-success">
                            <@spring.message 'auth.reset.success'/>
                        </div>
                        <div class="text-center mt-3">
                            <a href="/auth/login" class="btn btn-primary btn-sm">
                                <@spring.message 'auth.login.title'/>
                            </a>
                        </div>
                    <#elseif error??>
                        <div class="alert alert-danger">
                            <@spring.message 'auth.reset.error'/>
                        </div>
                    <#else>
                        <form method="post" action="/auth/reset-password">
                            <#if _csrf??>
                                <input type="hidden"
                                       name="${(_csrf.parameterName)!'csrf-param-name'}"
                                       value="${(_csrf.token)!'csrf-token'}"/>
                            </#if>
                            <input type="hidden" name="token" value="${token!}"/>
                            <div class="mb-3">
                                <label class="form-label"><@spring.message 'auth.reset.password'/></label>
                                <input type="password" name="password" class="form-control"
                                       required minlength="8"/>
                            </div>
                            <div class="d-grid">
                                <button type="submit" class="btn btn-primary">
                                    <@spring.message 'auth.reset.submit'/>
                                </button>
                            </div>
                        </form>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>