<#import "../layout/base.ftlh" as layout>
<#import "/spring.ftl" as spring>
<@layout.base>
    <div class="card mx-auto mt-4" style="max-width: 600px;">
        <div class="card-body">
            <h3 class="mb-4">${vacancyDto.id??then("Редактировать вакансию", "Создать вакансию")}</h3>
            <form action="${vacancyDto.id??then("/vacancies/" + vacancyDto.id + "/edit", "/vacancies/create")}" method="post">
                <#if _csrf??>
                    <input type="hidden"
                           name="${(_csrf.parameterName)!'csrf'}"
                           value="${(_csrf.token)!''}"/>
                </#if>
                <div class="mb-3">
                    <label class="form-label">Название вакансии</label>
                    <@spring.formInput "vacancyDto.name" 'class="form-control"'/>
                    <@spring.showErrors "<br>" "text-danger small"/>
                </div>
                <div class="mb-3">
                    <label class="form-label">Описание</label>
                    <@spring.formTextarea "vacancyDto.description" 'class="form-control" rows="4"'/>
                    <@spring.showErrors "<br>" "text-danger small"/>
                </div>
                <div class="mb-3">
                    <label class="form-label">Категория ID</label>
                    <@spring.formInput "vacancyDto.categoryId" 'class="form-control" type="number"'/>
                    <@spring.showErrors "<br>" "text-danger small"/>
                </div>
                <div class="mb-3">
                    <label class="form-label">Зарплата</label>
                    <@spring.formInput "vacancyDto.salary" 'class="form-control" type="number"'/>
                    <@spring.showErrors "<br>" "text-danger small"/>
                </div>
                <div class="row">
                    <div class="col mb-3">
                        <label class="form-label">Опыт от (лет)</label>
                        <@spring.formInput "vacancyDto.expFrom" 'class="form-control" type="number"'/>
                    </div>
                    <div class="col mb-3">
                        <label class="form-label">Опыт до (лет)</label>
                        <@spring.formInput "vacancyDto.expTo" 'class="form-control" type="number"'/>
                    </div>
                </div>
                <div class="mb-3 form-check">
                    <input type="checkbox" class="form-check-input" name="isActive"
                            ${(vacancyDto.active)??then("checked", "")}>
                    <label class="form-check-label">Активна</label>
                </div>
                <div class="d-flex gap-2">
                    <button type="submit" class="btn btn-primary">Сохранить</button>
                    <a href="/profile" class="btn btn-outline-secondary">Отмена</a>
                </div>
            </form>
        </div>
    </div>
</@layout.base>