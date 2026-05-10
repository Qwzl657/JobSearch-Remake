<#import "/spring.ftl" as spring>
<#macro layout>
    <!DOCTYPE html>
    <html lang="${.locale}">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>JobSearch</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
              crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
                crossorigin="anonymous"></script>
    </head>
    <body>

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-2">
        <div class="container-fluid">
            <a class="navbar-brand" href="/vacancies">JobSearch</a>

            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="/vacancies"><@spring.message 'nav.vacancies'/></a>
                    </li>
                    <#assign known = SPRING_SECURITY_CONTEXT??>
                    <#if known>
                        <#assign principal = SPRING_SECURITY_CONTEXT.authentication.principal>
                        <#list principal.authorities as auth>
                            <#if auth.authority == "ROLE_EMPLOYER">
                                <li class="nav-item">
                                    <a class="nav-link" href="/resumes/all">
                                        <@spring.message 'nav.resumes'/>
                                    </a>
                                </li>
                            </#if>
                            <#if auth.authority == "ROLE_APPLICANT">
                                <li class="nav-item">
                                    <a class="nav-link" href="/employers">
                                        <@spring.message 'nav.employers'/>
                                    </a>
                                </li>
                            </#if>
                        </#list>
                    </#if>
                </ul>

                <ul class="navbar-nav ms-auto align-items-center">
                    <#if known>
                        <li class="nav-item">
                            <span class="text-white-50 small me-2">${principal.getUsername()}</span>
                        </li>
                    </#if>

                    <li class="nav-item">
                        <div class="dropdown">
                            <button class="btn btn-secondary btn-sm dropdown-toggle" type="button"
                                    data-bs-toggle="dropdown">
                                <@spring.message 'nav.language'/>
                            </button>
                            <ul class="dropdown-menu dropdown-menu-end">
                                <li><a class="dropdown-item" href="?lang=ru"><@spring.message 'nav.lang.ru'/></a></li>
                                <li><a class="dropdown-item" href="?lang=en"><@spring.message 'nav.lang.en'/></a></li>
                            </ul>
                        </div>
                    </li>

                    <#if known>
                        <li class="nav-item">
                            <a class="nav-link" href="/profile"><@spring.message 'nav.profile'/></a>
                        </li>
                        <li class="nav-item">
                            <form method="post" action="/logout" class="d-inline">
                                <#if _csrf??>
                                    <input type="hidden"
                                           name="${(_csrf.parameterName)!'csrf-param-name'}"
                                           value="${(_csrf.token)!'csrf-token'}"/>
                                </#if>
                                <button type="submit" class="btn btn-outline-light btn-sm ms-1">
                                    <@spring.message 'nav.logout'/>
                                </button>
                            </form>
                        </li>
                    <#else>
                        <li class="nav-item">
                            <a class="btn btn-outline-light btn-sm ms-1" href="/auth/login">
                                <@spring.message 'nav.login'/>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="btn btn-light btn-sm ms-1" href="/auth/register">
                                <@spring.message 'nav.register'/>
                            </a>
                        </li>
                    </#if>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container">
        <#nested>
    </div>

    <footer class="text-center mt-5 py-3 border-top text-muted small">
        <div class="container">© 2026 JobSearch</div>
    </footer>

    </body>
    </html>
</#macro>