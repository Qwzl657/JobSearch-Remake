<#import "/spring.ftl" as spring>
<#import "/layout/base.ftlh" as layout>

<@layout.layout>
    <div class="row justify-content-center">
        <div class="col-md-8">
            <nav aria-label="breadcrumb" class="mb-3">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item">
                        <a href="/profile"><@spring.message 'nav.profile'/></a>
                    </li>
                    <li class="breadcrumb-item">
                        <a href="/vacancies"><@spring.message 'nav.vacancies'/></a>
                    </li>
                    <li class="breadcrumb-item active">
                        <@spring.message 'vacancy.responses'/>
                    </li>
                </ol>
            </nav>
            <div class="card shadow-sm">
                <div class="card-header bg-white py-3">
                    <h5 class="mb-0 fw-bold">
                        <@spring.message 'vacancy.responses'/> — ${vacancy.name}
                    </h5>
                </div>
                <div class="card-body p-4">
                    <#if applicants?has_content>
                        <#list applicants as applicant>
                            <div class="card mb-3 border">
                                <div class="card-body d-flex align-items-center gap-3">
                                    <img src="/avatars/${applicant.avatar!}"
                                         class="rounded-circle"
                                         style="width:48px;height:48px;object-fit:cover;"
                                         alt="avatar"
                                         onerror="this.src='https://ui-avatars.com/api/?name=${applicant.name?url}+${applicant.surname?url}&size=48'"/>
                                    <div>
                                        <h6 class="fw-semibold mb-0">
                                            ${applicant.name} ${applicant.surname}
                                        </h6>
                                        <p class="text-muted small mb-0">${applicant.email}</p>
                                        <#if applicant.phoneNumber?has_content>
                                            <p class="text-muted small mb-0">
                                                ${applicant.phoneNumber}
                                            </p>
                                        </#if>
                                    </div>
                                </div>
                            </div>
                        </#list>
                    <#else>
                        <div class="text-center py-5">
                            <p class="text-muted">
                                <@spring.message 'vacancy.responses.empty'/>
                            </p>
                        </div>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</@layout.layout>