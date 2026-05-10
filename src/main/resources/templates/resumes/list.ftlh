<#import "/spring.ftl" as spring>
<#import "/layout/base.ftlh" as layout>

<@layout.layout>

    <div class="d-flex justify-content-between align-items-center mb-4">
        <h3 class="fw-bold mb-0"><@spring.message 'resume.title'/></h3>
        <#if SPRING_SECURITY_CONTEXT??>
            <#list SPRING_SECURITY_CONTEXT.authentication.principal.authorities as auth>
                <#if auth.authority == "ROLE_APPLICANT">
                    <a href="/resumes/create" class="btn btn-primary btn-sm">
                        <@spring.message 'resume.create'/>
                    </a>
                </#if>
            </#list>
        </#if>
    </div>

    <#if resumes?has_content>
        <div class="row row-cols-1 row-cols-md-2 g-4">
            <#list resumes as resume>
                <div class="col">
                    <div class="card shadow-sm h-100">
                        <div class="card-body">
                            <div class="d-flex justify-content-between align-items-start mb-2">
                                <h5 class="card-title mb-0">${resume.name}</h5>
                                <span class="badge <#if resume.active>bg-success<#else>bg-secondary</#if>">
                                    <#if resume.active>
                                        <@spring.message 'resume.active'/>
                                    <#else>
                                        <@spring.message 'resume.inactive'/>
                                    </#if>
                                </span>
                            </div>
                            <span class="text-success small">
                                <#if resume.salary??>${resume.salary} ₸<#else>—</#if>
                            </span>
                        </div>
                        <div class="card-footer bg-transparent d-flex justify-content-end border-0">
                            <#if SPRING_SECURITY_CONTEXT??>
                                <#list SPRING_SECURITY_CONTEXT.authentication.principal.authorities as auth>
                                    <#if auth.authority == "ROLE_APPLICANT">
                                        <a href="/resumes/${resume.id}/edit"
                                           class="btn btn-outline-secondary btn-sm">
                                            <@spring.message 'resume.edit'/>
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
            <p class="text-muted"><@spring.message 'resume.no.results'/></p>
            <#if SPRING_SECURITY_CONTEXT??>
                <#list SPRING_SECURITY_CONTEXT.authentication.principal.authorities as auth>
                    <#if auth.authority == "ROLE_APPLICANT">
                        <a href="/resumes/create" class="btn btn-primary mt-2">
                            <@spring.message 'resume.create'/>
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
                    <a class="page-link" href="/resumes/all?page=${currentPage - 1}">
                        <@spring.message 'page.prev'/>
                    </a>
                </li>
                <#list 0..(totalPages-1) as p>
                    <li class="page-item <#if (p == currentPage)>active</#if>">
                        <a class="page-link" href="/resumes/all?page=${p}">${p + 1}</a>
                    </li>
                </#list>
                <li class="page-item <#if (currentPage >= totalPages - 1)>disabled</#if>">
                    <a class="page-link" href="/resumes/all?page=${currentPage + 1}">
                        <@spring.message 'page.next'/>
                    </a>
                </li>
            </ul>
        </nav>
    </#if>

</@layout.layout>