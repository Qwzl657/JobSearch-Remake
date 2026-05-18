<#import "/spring.ftl" as spring>
<#import "/layout/base.ftlh" as layout>

<@layout.layout>

    <div class="d-flex justify-content-between align-items-center mb-4">
        <h3 class="fw-bold mb-0"><@spring.message 'vacancy.title'/></h3>
        <div class="d-flex gap-2 align-items-center">
            <form method="get" action="/vacancies" class="d-flex gap-2">
                <select name="sort" class="form-select form-select-sm"
                        style="width:auto;" onchange="this.form.submit()">
                    <option value="date" <#if sort == "date">selected</#if>>
                        <@spring.message 'vacancy.sort.date'/>
                    </option>
                    <option value="responses" <#if sort == "responses">selected</#if>>
                        <@spring.message 'vacancy.sort.responses'/>
                    </option>
                </select>
            </form>
            <#if SPRING_SECURITY_CONTEXT??>
                <#list SPRING_SECURITY_CONTEXT.authentication.principal.authorities as auth>
                    <#if auth.authority == "ROLE_EMPLOYER">
                        <a href="/vacancies/create" class="btn btn-primary btn-sm">
                            <@spring.message 'vacancy.create'/>
                        </a>
                    </#if>
                </#list>
            </#if>
        </div>
    </div>

    <#if vacancies?has_content>
        <div class="row row-cols-1 row-cols-md-2 g-4">
            <#list vacancies as vacancy>
                <div class="col">
                    <div class="card shadow-sm h-100">
                        <div class="card-body">
                            <div class="d-flex justify-content-between align-items-start mb-2">
                                <h5 class="card-title mb-0">${vacancy.name}</h5>
                                <#if vacancy.active>
                                    <span class="badge bg-success">
                                        <@spring.message 'vacancy.active'/>
                                    </span>
                                </#if>
                            </div>
                            <p class="card-text text-muted small mb-3">
                                ${vacancy.description!}
                            </p>
                            <div class="d-flex gap-3">
                                <span class="text-success small">
                                    <#if vacancy.salary??>${vacancy.salary} ₸<#else>—</#if>
                                </span>
                                <span class="text-muted small">
                                    <@spring.message 'vacancy.exp'/>:
                                    ${vacancy.expFrom!0} — ${vacancy.expTo!0}
                                    <@spring.message 'vacancy.exp.years'/>
                                </span>
                            </div>
                        </div>
                        <div class="card-footer bg-transparent d-flex
                                    justify-content-end border-0 gap-2">
                            <#if SPRING_SECURITY_CONTEXT??>
                                <#assign currentUser =
                                SPRING_SECURITY_CONTEXT.authentication.principal>
                                <#list currentUser.authorities as auth>

                                    <#if auth.authority == "ROLE_EMPLOYER"
                                    && vacancy.authorId == currentUser.id>
                                        <a href="/vacancies/${vacancy.id}/edit"
                                           class="btn btn-outline-secondary btn-sm">
                                            <@spring.message 'vacancy.edit'/>
                                        </a>
                                        <form method="post"
                                              action="/vacancies/${vacancy.id}/delete">
                                            <#if _csrf??>
                                                <input type="hidden"
                                                       name="${(_csrf.parameterName)!'_csrf'}"
                                                       value="${(_csrf.token)!''}"/>
                                            </#if>
                                            <button type="submit"
                                                    class="btn btn-outline-danger btn-sm"
                                                    onclick="return confirm(
                                                            '<@spring.message "confirm.vacancy.delete"/>')">
                                                <@spring.message 'vacancy.delete'/>
                                            </button>
                                        </form>
                                    </#if>

                                    <#if auth.authority == "ROLE_APPLICANT">
                                        <a href="/vacancies/${vacancy.id}/respond"
                                           class="btn btn-primary btn-sm">
                                            <@spring.message 'vacancy.respond'/>
                                        </a>
                                    </#if>

                                </#list>
                            </#if>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    <#else>
        <div class="text-center py-5">
            <p class="text-muted"><@spring.message 'vacancy.no.results'/></p>
            <#if SPRING_SECURITY_CONTEXT??>
                <#list SPRING_SECURITY_CONTEXT.authentication.principal.authorities as auth>
                    <#if auth.authority == "ROLE_EMPLOYER">
                        <a href="/vacancies/create" class="btn btn-primary mt-2">
                            <@spring.message 'vacancy.create'/>
                        </a>
                    </#if>
                </#list>
            </#if>
        </div>
    </#if>

    <#if (totalPages > 1)>
        <nav class="mt-5">
            <ul class="pagination justify-content-center">
                <li class="page-item <#if (currentPage == 0)>disabled</#if>">
                    <a class="page-link"
                       href="/vacancies?page=${currentPage - 1}&size=${size}&sort=${sort}">
                        <@spring.message 'page.prev'/>
                    </a>
                </li>
                <#list 0..(totalPages-1) as p>
                    <li class="page-item <#if (p == currentPage)>active</#if>">
                        <a class="page-link"
                           href="/vacancies?page=${p}&size=${size}&sort=${sort}">
                            ${p + 1}
                        </a>
                    </li>
                </#list>
                <li class="page-item <#if (currentPage >= totalPages - 1)>disabled</#if>">
                    <a class="page-link"
                       href="/vacancies?page=${currentPage + 1}&size=${size}&sort=${sort}">
                        <@spring.message 'page.next'/>
                    </a>
                </li>
            </ul>
        </nav>
    </#if>

</@layout.layout>