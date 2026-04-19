<#import "../layout/base.ftlh" as layout>
<@layout.base>
    <div class="row">
        <div class="col-md-4">
            <div class="card">
                <div class="card-body text-center">
                    <img src="/images/${user.avatar!'default.png'}"
                         class="rounded-circle mb-3"
                         style="width: 120px; height: 120px; object-fit: cover;"
                         alt="avatar"/>
                    <h4>${user.name} ${user.surname}</h4>
                    <p class="text-muted">${user.email}</p>
                    <p class="text-muted">${user.phoneNumber}</p>
                    <span class="badge bg-primary">${user.accountType}</span>
                    <div class="mt-3">
                        <a href="/profile/edit" class="btn btn-outline-secondary btn-sm">Редактировать</a>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-8">
            <#if user.accountType == "APPLICANT">
                <h5>Мои резюме</h5>
                <#if resumes?has_content>
                    <#list resumes as resume>
                        <div class="card mb-3">
                            <div class="card-body d-flex justify-content-between align-items-center">
                                <div>
                                    <h6 class="mb-1">${resume.name}</h6>
                                    <small class="text-muted">Обновлено: ${resume.updateTime}</small>
                                </div>
                                <div>
                                    <a href="/resumes/${resume.id}/edit" class="btn btn-sm btn-outline-primary">Редактировать</a>
                                    <a href="/resumes/${resume.id}/update" class="btn btn-sm btn-outline-secondary">Обновить</a>
                                </div>
                            </div>
                        </div>
                    </#list>
                <#else>
                    <p class="text-muted">У вас пока нет резюме</p>
                </#if>
                <a href="/resumes/create" class="btn btn-primary mt-2">Создать резюме</a>
            </#if>

            <#if user.accountType == "EMPLOYER">
                <h5>Мои вакансии</h5>
                <#if vacancies?has_content>
                    <#list vacancies as vacancy>
                        <div class="card mb-3">
                            <div class="card-body d-flex justify-content-between align-items-center">
                                <div>
                                    <h6 class="mb-1">${vacancy.name}</h6>
                                    <small class="text-muted">Обновлено: ${vacancy.updateTime}</small>
                                </div>
                                <div>
                                    <a href="/vacancies/${vacancy.id}/edit" class="btn btn-sm btn-outline-primary">Редактировать</a>
                                    <a href="/vacancies/${vacancy.id}/update" class="btn btn-sm btn-outline-secondary">Обновить</a>
                                </div>
                            </div>
                        </div>
                    </#list>
                <#else>
                    <p class="text-muted">У вас пока нет вакансий</p>
                </#if>
                <a href="/vacancies/create" class="btn btn-primary mt-2">Создать вакансию</a>
            </#if>
        </div>
    </div>
</@layout.base>