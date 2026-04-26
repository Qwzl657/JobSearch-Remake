<#import "../layout/base.ftlh" as layout>
<@layout.base>

     <h4 class="mb-3">Вакансии</h4>

    <!-- Сортировка -->
    <div class="mb-3">
        <select class="form-select w-auto d-inline"
                onchange="window.location.href='/vacancies?sort='+this.value+'&page=0'">
            <option value="date" ${(sort == "date")?then("selected", "")}>По дате</option>
            <option value="responses" ${(sort == "responses")?then("selected", "")}>По откликам</option>
        </select>
    </div>

    <!-- Список вакансий -->
    <div class="row row-cols-1 row-cols-md-2 g-4">
        <#if vacancies?has_content>
            <#list vacancies as vacancy>
                <div class="col">
                    <div class="card h-100">
                        <div class="card-body">
                            <h5 class="card-title">${vacancy.name}</h5>
                            <p class="text-muted">${vacancy.salary} сом</p>
                            <p class="card-text">${vacancy.description}</p>
                            <small class="text-muted">
                                Опыт: ${vacancy.expFrom} - ${vacancy.expTo} лет
                            </small>
                        </div>
                        <div class="card-footer d-flex gap-2">
                            <a href="/vacancies/${vacancy.id}" class="btn btn-sm btn-dark">Подробнее</a>
                        </div>
                    </div>
                </div>
            </#list>
        <#else>
            <p class="text-muted">Вакансий пока нет</p>
        </#if>
    </div>

    <!-- Пагинация -->
    <#if totalPages gt 1>
        <nav class="mt-4">
            <ul class="pagination justify-content-center">
                <#if currentPage gt 0>
                    <li class="page-item">
                        <a class="page-link"
                           href="?page=${currentPage - 1}&size=5&sort=${sort!'date'}">Назад</a>
                    </li>
                </#if>

                <#list 0..(totalPages-1) as p>
                    <li class="page-item ${(p == currentPage)?then("active", "")}">
                        <a class="page-link"
                           href="?page=${p}&size=5&sort=${sort!'date'}">${p + 1}</a>
                    </li>
                </#list>

                <#if currentPage lt totalPages - 1>
                    <li class="page-item">
                        <a class="page-link"
                           href="?page=${currentPage + 1}&size=5&sort=${sort!'date'}">Вперёд</a>
                    </li>
                </#if>
            </ul>
        </nav>
    </#if>

</@layout.base>