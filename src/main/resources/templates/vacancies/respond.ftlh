<#import "/spring.ftl" as spring>
<#import "/layout/base.ftlh" as layout>

<@layout.layout>

    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-sm">
                <div class="card-header bg-white py-3">
                    <h5 class="mb-0 fw-bold">
                        <@spring.message 'vacancy.respond.choose'/>
                    </h5>
                </div>
                <div class="card-body p-4">

                    <#if alreadyResponded??>
                        <div class="alert alert-warning">
                            <@spring.message 'vacancy.respond.already'/>
                        </div>
                    </#if>

                    <#if resumes?has_content>
                        <form method="post" action="/vacancies/${vacancy.id}/respond">
                            <#if _csrf??>
                                <input type="hidden"
                                       name="${(_csrf.parameterName)!'csrf-param-name'}"
                                       value="${(_csrf.token)!'csrf-token'}"/>
                            </#if>
                            <div class="mb-3">
                                <label class="form-label">
                                    <@spring.message 'vacancy.respond.choose'/>
                                </label>
                                <select name="resumeId" class="form-select">
                                    <#list resumes as resume>
                                        <option value="${resume.id}">${resume.name}</option>
                                    </#list>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary">
                                <@spring.message 'vacancy.respond.submit'/>
                            </button>
                        </form>
                    <#else>
                        <p class="text-muted">
                            <@spring.message 'vacancy.respond.no.resumes'/>
                        </p>
                        <a href="/resumes/create" class="btn btn-outline-primary btn-sm">
                            <@spring.message 'resume.create'/>
                        </a>
                    </#if>

                </div>
            </div>
        </div>
    </div>

</@layout.layout>