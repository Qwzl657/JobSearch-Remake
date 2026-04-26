<#import "../layout/base.ftlh" as layout>
<@layout.base>

    <div class="row">
        <!-- Левая колонка — данные пользователя -->
        <div class="col-md-4">
            <div class="card">
                <div class="card-body text-center">
                    <img src="/avatars/${user.avatar!'default.png'}"
                         class="rounded-circle mb-3"
                         style="width: 120px; height: 120px; object-fit: cover;"
                         alt="avatar"/>
                    <h4>${user.name} ${user.surname}</h4>
                    <p class="text-muted">${user.email}</p>
                    <p class="text-muted">${user.phoneNumber}</p>
                    <span class="badge bg-primary">${user.accountType}</span>
                    <div class="mt-3">
                        <a href="/profile/edit" class="btn btn-outline-secondary btn-sm">
                            Редактировать
                        </a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Правая колонка — резюме или вакансии -->
        <div class="col-md-8">
            <#if user.accountType == "APPLICANT">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h5>Мои резюме</h5>
                    <a href="/resumes/create" class="btn btn-primary btn-sm">Создать резюме</a>
                </div>
                <#if resumes?has_content>
                    <#list resumes as resume>
                        <div class="card mb-3">
                            <div class="card-body d-flex justify-content-between align-items-center">
                                <div>
                                    <h6 class="mb-1">${resume.name}</h6>
                                    <small class="text-muted">Зарплата: ${resume.salary} сом</small><br>
                                    <small class="text-muted">Обновлено: ${resume.updateTime}</small>
                                </div>
                                <div class="d-flex gap-2">
                                    <a href="/resumes/${resume.id}/edit"
                                       class="btn btn-sm btn-outline-primary">Редактировать</a>
                                </div>
                            </div>
                        </div>
                    </#list>
                <#else>
                    <p class="text-muted">У вас пока нет резюме</p>
                </#if>
            </#if>

            <#if user.accountType == "EMPLOYER">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h5>Мои вакансии</h5>
                    <a href="/vacancies/create" class="btn btn-primary btn-sm">Создать вакансию</a>
                </div>
                <#if vacancies?has_content>
                    <#list vacancies as vacancy>
                        <div class="card mb-3">
                            <div class="card-body d-flex justify-content-between align-items-center">
                                <div>
                                    <h6 class="mb-1">${vacancy.name}</h6>
                                    <small class="text-muted">Зарплата: ${vacancy.salary} сом</small><br>
                                    <small class="text-muted">Обновлено: ${vacancy.updateTime}</small>
                                </div>
                                <div class="d-flex gap-2">
                                    <a href="/vacancies/${vacancy.id}/edit"
                                       class="btn btn-sm btn-outline-primary">Редактировать</a>
                                </div>
                            </div>
                        </div>
                    </#list>
                <#else>
                    <p class="text-muted">У вас пока нет вакансий</p>
                </#if>
            </#if>

            <!-- Пагинация -->
            <#if totalPages?? && totalPages gt 1>
                <nav class="mt-3">
                    <ul class="pagination justify-content-center">
                        <#if currentPage gt 0>
                            <li class="page-item">
                                <a class="page-link" href="?page=${currentPage - 1}&size=5">Назад</a>
                            </li>
                        </#if>

                        <#list 0..(totalPages-1) as p>
                            <li class="page-item ${(p == currentPage)?then("active", "")}">
                                <a class="page-link" href="?page=${p}&size=5">${p + 1}</a>
                            </li>
                        </#list>

                        <#if currentPage lt totalPages - 1>
                            <li class="page-item">
                                <a class="page-link" href="?page=${currentPage + 1}&size=5">Вперёд</a>
                            </li>
                        </#if>
                    </ul>
                </nav>
            </#if>
        </div>
    </div>

</@layout.base>