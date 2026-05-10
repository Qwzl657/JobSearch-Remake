<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="${.locale}">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><@spring.message 'auth.register.title'/> — JobSearch</title>
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
        <div class="col-md-6">
            <div class="card shadow-sm">
                <div class="card-body p-4">
                    <h4 class="card-title text-center mb-4">
                        <@spring.message 'auth.register.title'/>
                    </h4>

                    <form method="post" action="/auth/register">
                        <#if _csrf??>
                            <input type="hidden"
                                   name="${(_csrf.parameterName)!'csrf-param-name'}"
                                   value="${(_csrf.token)!'csrf-token'}"/>
                        </#if>
                        <div class="row g-3 mb-3">
                            <div class="col-md-6">
                                <label class="form-label"><@spring.message 'auth.register.name'/></label>
                                <@spring.formInput "userCreateDto.name" 'class="form-control"'/>
                                <@spring.showErrors "<br>" "text-danger small"/>
                            </div>
                            <div class="col-md-6">
                                <label class="form-label"><@spring.message 'auth.register.surname'/></label>
                                <@spring.formInput "userCreateDto.surname" 'class="form-control"'/>
                                <@spring.showErrors "<br>" "text-danger small"/>
                            </div>
                        </div>
                        <div class="row g-3 mb-3">
                            <div class="col-md-5">
                                <label class="form-label"><@spring.message 'auth.register.age'/></label>
                                <@spring.formInput "userCreateDto.age" 'class="form-control" type="number" min="14" max="100"'/>
                                <@spring.showErrors "<br>" "text-danger small"/>
                            </div>
                            <div class="col-md-7">
                                <label class="form-label"><@spring.message 'auth.register.phone'/></label>
                                <@spring.formInput "userCreateDto.phoneNumber" 'class="form-control"'/>
                                <@spring.showErrors "<br>" "text-danger small"/>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label class="form-label"><@spring.message 'auth.register.email'/></label>
                            <@spring.formInput "userCreateDto.email" 'class="form-control" type="email"'/>
                            <@spring.showErrors "<br>" "text-danger small"/>
                        </div>
                        <div class="mb-3">
                            <label class="form-label"><@spring.message 'auth.register.type'/></label>
                            <div class="d-flex gap-3">
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="accountType"
                                           value="APPLICANT" id="applicant" checked/>
                                    <label class="form-check-label" for="applicant">
                                        <@spring.message 'auth.register.type.applicant'/>
                                    </label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="accountType"
                                           value="EMPLOYER" id="employer"/>
                                    <label class="form-check-label" for="employer">
                                        <@spring.message 'auth.register.type.employer'/>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="mb-4">
                            <label class="form-label"><@spring.message 'auth.register.password'/></label>
                            <@spring.formPasswordInput "userCreateDto.password" 'class="form-control"'/>
                            <@spring.showErrors "<br>" "text-danger small"/>
                        </div>
                        <div class="d-grid mb-3">
                            <button type="submit" class="btn btn-primary">
                                <@spring.message 'auth.register.submit'/>
                            </button>
                        </div>
                    </form>

                    <div class="text-center small">
                        <a href="/auth/login"><@spring.message 'auth.register.have.account'/></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>