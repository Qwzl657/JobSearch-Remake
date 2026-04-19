<#import "../layout/base.ftlh" as layout>
<@layout.base>
    <h4 class="mb-4">Вакансии</h4>
    <div class="row row-cols-1 row-cols-md-2 g-4">
        <#if vacancies?has_content>
            <#list vacancies as vacancy>
                <div class="col">
                    <div class="card h-100">
                        <div class="card-body">
                            <h5 class="card-title">${vacancy.name}</h5>
                            <p class="text-muted">${vacancy.salary} сом</p>
                            <p class="card-text">${vacancy.description}</p>
                            <small class="text-muted">Опыт: ${vacancy.expFrom} - ${vacancy.expTo} лет</small>
                        </div>
                        <div class="card-footer">
                            <a href="/vacancies/${vacancy.id}" class="btn btn-sm btn-dark">Подробнее</a>
                        </div>
                    </div>
                </div>
            </#list>
        <#else>
            <p class="text-muted">Вакансий пока нет</p>
        </#if>
    </div>
</@layout.base>