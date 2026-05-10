<#import "/spring.ftl" as spring>
<#import "/layout/base.ftlh" as layout>

<@layout.layout>

    <div class="d-flex justify-content-between align-items-center mb-4">
        <h3 class="fw-bold mb-0"><@spring.message 'nav.employers'/></h3>
    </div>

    <#if employers?has_content>
        <div class="row row-cols-1 row-cols-md-2 g-4">
            <#list employers as employer>
                <div class="col">
                    <div class="card shadow-sm h-100">
                        <div class="card-body d-flex align-items-center gap-3">
                            <img src="/avatars/${employer.avatar!}"
                                 class="rounded-circle"
                                 style="width:56px;height:56px;object-fit:cover;"
                                 alt="avatar"
                                 onerror="this.src='https://ui-avatars.com/api/?name=${employer.name?url}+${employer.surname?url}&size=56'"/>
                            <div>
                                <h6 class="fw-semibold mb-0">
                                    ${employer.name} ${employer.surname}
                                </h6>
                                <p class="text-muted small mb-0">${employer.email}</p>
                                <#if employer.phoneNumber?has_content>
                                    <p class="text-muted small mb-0">${employer.phoneNumber}</p>
                                </#if>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    <#else>
        <div class="text-center py-5">
            <p class="text-muted"><@spring.message 'employers.empty'/></p>
        </div>
    </#if>

</@layout.layout>