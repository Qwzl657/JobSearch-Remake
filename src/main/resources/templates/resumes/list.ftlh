<#import "../layout/base.ftlh" as layout>
<@layout.base>
    <h4 class="mb-4">Резюме</h4>
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
                        <div class="card-footer">
                            <a href="/resumes/${resume.id}" class="btn btn-sm btn-dark">Подробнее</a>
                        </div>
                    </div>
                </div>
            </#list>
        <#else>
            <p class="text-muted">Резюме пока нет</p>
        </#if>
    </div>
</@layout.base>