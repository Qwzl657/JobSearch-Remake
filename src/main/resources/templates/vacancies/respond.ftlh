<#import "/spring.ftl" as spring>
<#import "/layout/base.ftlh" as layout>

<@layout.layout>
    <div class="row justify-content-center">
        <div class="col-md-7">
            <nav aria-label="breadcrumb" class="mb-3">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item">
                        <a href="/vacancies"><@spring.message 'nav.vacancies'/></a>
                    </li>
                    <li class="breadcrumb-item active">
                        <@spring.message 'vacancy.respond'/>
                    </li>
                </ol>
            </nav>
            <div class="card shadow-sm">
                <div class="card-header bg-white py-3">
                    <h5 class="mb-0 fw-bold">${vacancy.name}</h5>
                    <p class="text-muted small mb-0">${vacancy.description!}</p>
                </div>
                <div class="card-body p-4">
                    <#if resumes?has_content>
                        <p class="mb-3"><@spring.message 'vacancy.respond.choose'/></p>
                        <#list resumes as resume>
                            <div class="card mb-3 border">
                                <div class="card-body d-flex justify-content-between align-items-center">
                                    <div>
                                        <h6 class="fw-semibold mb-1">${resume.name}</h6>
                                        <span class="text-muted small">
                                            <#if resume.salary??>${resume.salary} ₸<#else>—</#if>
                                        </span>
                                    </div>
                                    <form method="post" action="/vacancies/${vacancy.id}/respond">
                                        <#if _csrf??>
                                            <input type="hidden"
                                                   name="${(_csrf.parameterName)!'_csrf'}"
                                                   value="${(_csrf.token)!''}"/>
                                        </#if>
                                        <input type="hidden" name="resumeId" value="${resume.id}"/>
                                        <button type="submit" class="btn btn-primary btn-sm">
                                            <@spring.message 'vacancy.respond.submit'/>
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </#list>
                    <#else>
                        <div class="text-center py-4">
                            <p class="text-muted mb-3">
                                <@spring.message 'vacancy.respond.no.resumes'/>
                            </p>
                            <a href="/resumes/create" class="btn btn-primary btn-sm">
                                <@spring.message 'resume.create'/>
                            </a>
                        </div>
                    </#if>
                    <div class="mt-3">
                        <a href="/vacancies" class="btn btn-outline-secondary btn-sm">
                            <@spring.message 'profile.edit.cancel'/>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@layout.layout>