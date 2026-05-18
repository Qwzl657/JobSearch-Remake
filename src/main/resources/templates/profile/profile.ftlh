<#import "/spring.ftl" as spring>
<#import "/layout/base.ftlh" as layout>

<@layout.layout>

    <div class="row g-4">

        <div class="col-md-4">
            <div class="card shadow-sm text-center p-4">
                <div class="mb-3">
                    <img src="/avatars/${user.avatar!}"
                         class="rounded-circle border border-3 border-primary"
                         style="width:110px;height:110px;object-fit:cover;"
                         alt="avatar"
                         onerror="this.src='https://ui-avatars.com/api/?name=${user.name?url}+${user.surname?url}&size=110'"/>
                </div>
                <h5 class="fw-bold mb-1">${user.name} ${user.surname}</h5>
                <p class="text-muted small mb-1">${user.email}</p>
                <#if user.phoneNumber?has_content>
                    <p class="text-muted small mb-2">${user.phoneNumber}</p>
                </#if>
                <#if user.age??>
                    <p class="text-muted small mb-2">
                        ${user.age} <@spring.message 'profile.age.years'/>
                    </p>
                </#if>
                <span class="badge <#if user.accountType == 'APPLICANT'>bg-primary<#else>bg-success</#if> mb-3">
                    <#if user.accountType == "APPLICANT">
                        <@spring.message 'profile.type.applicant'/>
                    <#else>
                        <@spring.message 'profile.type.employer'/>
                    </#if>
                </span>
                <a href="/profile/edit" class="btn btn-outline-primary btn-sm">
                    <@spring.message 'profile.edit'/>
                </a>
            </div>
        </div>

        <div class="col-md-8">
            <#if user.accountType == "APPLICANT">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h5 class="fw-bold mb-0"><@spring.message 'resume.title'/></h5>
                    <a href="/resumes/create" class="btn btn-primary btn-sm">
                        <@spring.message 'resume.create'/>
                    </a>
                </div>
                <#if resumes?has_content>
                    <#list resumes as resume>
                        <div class="card shadow-sm mb-3">
                            <div class="card-body d-flex justify-content-between align-items-center">
                                <div>
                                    <h6 class="fw-semibold mb-1">${resume.name}</h6>
                                    <span class="text-success small">
                                        <#if resume.salary??>${resume.salary} ₸<#else>—</#if>
                                    </span>
                                    <span class="badge ms-2 <#if resume.active>bg-success<#else>bg-secondary</#if>">
                                        <#if resume.active>
                                            <@spring.message 'resume.active'/>
                                        <#else>
                                            <@spring.message 'resume.inactive'/>
                                        </#if>
                                    </span>
                                </div>
                                <div class="d-flex gap-2">
                                    <a href="/resumes/${resume.id}/edit"
                                       class="btn btn-outline-secondary btn-sm">
                                        <@spring.message 'resume.edit'/>
                                    </a>
                                    <form method="post" action="/resumes/${resume.id}/delete">
                                        <#if _csrf??>
                                            <input type="hidden"
                                                   name="${(_csrf.parameterName)!'_csrf'}"
                                                   value="${(_csrf.token)!''}"/>
                                        </#if>
                                        <button type="submit" class="btn btn-outline-danger btn-sm">
                                            <@spring.message 'resume.delete'/>
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </#list>
                <#else>
                    <p class="text-muted"><@spring.message 'resume.no.results'/></p>
                </#if>
            <#else>
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h5 class="fw-bold mb-0"><@spring.message 'vacancy.title'/></h5>
                    <a href="/vacancies/create" class="btn btn-primary btn-sm">
                        <@spring.message 'vacancy.create'/>
                    </a>
                </div>
                <#if vacancies?has_content>
                    <#list vacancies as vacancy>
                        <div class="card shadow-sm mb-3">
                            <div class="card-body d-flex justify-content-between align-items-center">
                                <div>
                                    <h6 class="fw-semibold mb-1">${vacancy.name}</h6>
                                    <span class="text-success small">
                                        <#if vacancy.salary??>${vacancy.salary} ₸<#else>—</#if>
                                    </span>
                                    <#if vacancy.active>
                                        <span class="badge bg-success ms-2">
                                            <@spring.message 'vacancy.active'/>
                                        </span>
                                    </#if>
                                </div>
                                <div class="d-flex gap-2">
                                    <a href="/vacancies/${vacancy.id}/edit"
                                       class="btn btn-outline-secondary btn-sm">
                                        <@spring.message 'vacancy.edit'/>
                                    </a>
                                    <form method="post" action="/resumes/${resume.id}/delete">
                                        <#if _csrf??>
                                            <input type="hidden"
                                                   name="${(_csrf.parameterName)!'_csrf'}"
                                                   value="${(_csrf.token)!''}"/>
                                        </#if>
                                        <button type="submit"
                                                class="btn btn-outline-danger btn-sm"
                                                onclick="return confirm('<@spring.message "confirm.resume.delete"/>')">
                                            <@spring.message 'resume.delete'/>
                                        </button>
                                    </form>

                                    <form method="post" action="/vacancies/${vacancy.id}/delete">
                                        <#if _csrf??>
                                            <input type="hidden"
                                                   name="${(_csrf.parameterName)!'_csrf'}"
                                                   value="${(_csrf.token)!''}"/>
                                        </#if>
                                        <button type="submit"
                                                class="btn btn-outline-danger btn-sm"
                                                onclick="return confirm('<@spring.message "confirm.vacancy.delete"/>')">
                                            <@spring.message 'vacancy.delete'/>
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </#list>
                <#else>
                    <p class="text-muted"><@spring.message 'vacancy.no.results'/></p>
                </#if>
            </#if>

            <#if (totalPages > 1)>
                <nav class="mt-3">
                    <ul class="pagination justify-content-center">
                        <li class="page-item <#if (currentPage == 0)>disabled</#if>">
                            <a class="page-link"
                               href="/profile?page=${currentPage - 1}&size=${param.size!5}">
                                <@spring.message 'page.prev'/>
                            </a>
                        </li>
                        <#list 0..(totalPages-1) as p>
                            <li class="page-item <#if (p == currentPage)>active</#if>">
                                <a class="page-link"
                                   href="/profile?page=${p}&size=${param.size!5}">${p + 1}</a>
                            </li>
                        </#list>
                        <li class="page-item <#if (currentPage >= totalPages - 1)>disabled</#if>">
                            <a class="page-link"
                               href="/profile?page=${currentPage + 1}&size=${param.size!5}">
                                <@spring.message 'page.next'/>
                            </a>
                        </li>
                    </ul>
                </nav>
            </#if>
        </div>
    </div>

</@layout.layout>