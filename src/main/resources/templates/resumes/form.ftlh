<#import "../layout/base.ftlh" as layout>
<#import "/spring.ftl" as spring>
<@layout.base>
    <div class="card mx-auto mt-4" style="max-width: 600px;">
        <div class="card-body">
            <h3 class="mb-4">${resumeDto.id??then("Редактировать резюме", "Создать резюме")}</h3>
            <form action="${resumeDto.id??then("/resumes/" + resumeDto.id + "/edit", "/resumes/create")}" method="post">
                <#if _csrf??>
                    <input type="hidden"
                           name="${(_csrf.parameterName)!'csrf'}"
                           value="${(_csrf.token)!''}"/>
                </#if>

                <div class="mb-3">
                    <label class="form-label">Название резюме</label>
                    <@spring.formInput "resumeDto.name" 'class="form-control"'/>
                    <@spring.showErrors "<br>" "text-danger small"/>
                </div>

                <div class="mb-3">
                    <label class="form-label">Категория ID</label>
                    <@spring.formInput "resumeDto.categoryId" 'class="form-control" type="number"'/>
                    <@spring.showErrors "<br>" "text-danger small"/>
                </div>

                <div class="mb-3">
                    <label class="form-label">Ожидаемая зарплата</label>
                    <@spring.formInput "resumeDto.salary" 'class="form-control" type="number"'/>
                    <@spring.showErrors "<br>" "text-danger small"/>
                </div>

                <div class="mb-3 form-check">
                    <input type="hidden" name="isActive" value="false"/>
                    <input type="checkbox"
                           class="form-check-input"
                           name="isActive"
                           value="true"
                            ${(resumeDto.isActive())?then("checked", "")}>
                    <label class="form-check-label">Активно</label>
                </div>

                <div class="d-flex gap-2">
                    <button type="submit" class="btn btn-primary">Сохранить</button>
                    <a href="/profile" class="btn btn-outline-secondary">Отмена</a>
                </div>
            </form>
        </div>
    </div>
</@layout.base>