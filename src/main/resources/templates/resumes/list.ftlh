<#import "../layout/base.ftlh" as layout>
<@layout.base>

    <h4 class="mb-3">Резюме</h4>

    <!-- Список резюме -->
    <div class="row row-cols-1 row-cols-md-2 g-4">
        <#if resumes?has_content>
            <#list resumes as resume>
                <div class="col">
                    <div class="card h-100">
                        <div class="card-body">
                            <h5 class="card-title">${resume.name}</h5>
                            <p class="text-muted">Зарплата: ${resume.salary} сом</p>
                            <small class="text-muted">Обновлено: ${resume.updateTime}</small>
                        </div>
                        <div class="card-footer d-flex gap-2">
                            <a href="/resumes/${resume.id}" class="btn btn-sm btn-dark">Подробнее</a>
                        </div>
                    </div>
                </div>
            </#list>
        <#else>
            <p class="text-muted">Резюме пока нет</p>
        </#if>
    </div>

    <!-- Пагинация -->
    <#if totalPages gt 1>
        <nav class="mt-4">
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

</@layout.base>