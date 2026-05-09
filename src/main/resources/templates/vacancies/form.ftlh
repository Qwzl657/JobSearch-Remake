<#import "/spring.ftl" as spring>
<#import "/layout/base.ftlh" as layout>

<@layout.layout>

    <div class="row justify-content-center">
        <div class="col-md-7">
            <nav aria-label="breadcrumb" class="mb-3">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="/profile"><@spring.message 'nav.profile'/></a></li>
                    <li class="breadcrumb-item"><a href="/vacancies"><@spring.message 'vacancy.title'/></a></li>
                    <li class="breadcrumb-item active">
                        <#if vacancyDto.id??><@spring.message 'vacancy.form.title.edit'/>
                        <#else><@spring.message 'vacancy.form.title.create'/></#if>
                    </li>
                </ol>
            </nav>
            <div class="card shadow-sm">
                <div class="card-header bg-white py-3">
                    <h5 class="mb-0 fw-bold">
                        <#if vacancyDto.id??><@spring.message 'vacancy.form.title.edit'/>
                        <#else><@spring.message 'vacancy.form.title.create'/></#if>
                    </h5>
                </div>
                <div class="card-body p-4">
                    <form method="post"
                          action="<#if vacancyDto.id??>/vacancies/${vacancyDto.id}/edit<#else>/vacancies/create</#if>">
                        <#if _csrf??>
                            <input type="hidden"
                                   name="${(_csrf.parameterName)!'csrf-param-name'}"
                                   value="${(_csrf.token)!'csrf-token'}"/>
                        </#if>
                        <div class="mb-3">
                            <label class="form-label"><@spring.message 'vacancy.name'/></label>
                            <input type="text" name="name" value="${vacancyDto.name!}"
                                   class="form-control" required/>
                        </div>
                        <div class="mb-3">
                            <label class="form-label"><@spring.message 'vacancy.description'/></label>
                            <textarea name="description" class="form-control" rows="4">${vacancyDto.description!}</textarea>
                        </div>
                        <div class="mb-3">
                            <label class="form-label"><@spring.message 'vacancy.salary'/></label>
                            <div class="input-group" style="max-width:200px;">
                                <input type="number" name="salary" value="${vacancyDto.salary!}"
                                       class="form-control" min="0"/>
                                <span class="input-group-text">₸</span>
                            </div>
                        </div>
                        <div class="row mb-3 g-3">
                            <div class="col-md-6">
                                <label class="form-label">
                                    <@spring.message 'vacancy.exp'/> (<@spring.message 'vacancy.exp.from'/>)
                                </label>
                                <div class="input-group">
                                    <input type="number" name="expFrom" value="${vacancyDto.expFrom!}"
                                           class="form-control" min="0"/>
                                    <span class="input-group-text"><@spring.message 'vacancy.exp.years'/></span>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <label class="form-label">
                                    <@spring.message 'vacancy.exp'/> (<@spring.message 'vacancy.exp.to'/>)
                                </label>
                                <div class="input-group">
                                    <input type="number" name="expTo" value="${vacancyDto.expTo!}"
                                           class="form-control" min="0"/>
                                    <span class="input-group-text"><@spring.message 'vacancy.exp.years'/></span>
                                </div>
                            </div>
                        </div>
                        <div class="mb-4">
                            <div class="form-check">
                                <input type="checkbox" name="active" class="form-check-input" id="activeCheck"
                                       <#if vacancyDto.active?? && vacancyDto.active>checked</#if>/>
                                <label class="form-check-label" for="activeCheck">
                                    <@spring.message 'vacancy.active'/>
                                </label>
                            </div>
                        </div>
                        <div class="d-flex gap-2">
                            <button type="submit" class="btn btn-primary">
                                <@spring.message 'vacancy.form.submit'/>
                            </button>
                            <a href="/profile" class="btn btn-outline-secondary">
                                <@spring.message 'profile.edit.cancel'/>
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

</@layout.layout>