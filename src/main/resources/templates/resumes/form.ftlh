<#import "/spring.ftl" as spring>
<#import "/layout/base.ftlh" as layout>

<@layout.layout>

    <div class="row justify-content-center">
        <div class="col-md-7">
            <nav aria-label="breadcrumb" class="mb-3">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="/profile"><@spring.message 'nav.profile'/></a></li>
                    <li class="breadcrumb-item"><a href="/resumes/all"><@spring.message 'resume.title'/></a></li>
                    <li class="breadcrumb-item active">
                        <#if resumeDto.id??><@spring.message 'resume.form.title.edit'/>
                        <#else><@spring.message 'resume.form.title.create'/></#if>
                    </li>
                </ol>
            </nav>
            <div class="card shadow-sm">
                <div class="card-header bg-white py-3">
                    <h5 class="mb-0 fw-bold">
                        <#if resumeDto.id??><@spring.message 'resume.form.title.edit'/>
                        <#else><@spring.message 'resume.form.title.create'/></#if>
                    </h5>
                </div>
                <div class="card-body p-4">
                    <form method="post"
                          action="<#if resumeDto.id??>/resumes/${resumeDto.id}/edit<#else>/resumes/create</#if>">
                        <#if _csrf??>
                            <input type="hidden"
                                   name="${(_csrf.parameterName)!'csrf-param-name'}"
                                   value="${(_csrf.token)!'csrf-token'}"/>
                        </#if>
                        <div class="mb-3">
                            <label class="form-label"><@spring.message 'resume.name'/></label>
                            <input type="text" name="name" value="${resumeDto.name!}"
                                   class="form-control" required/>
                        </div>
                        <div class="mb-3">
                            <label class="form-label"><@spring.message 'resume.salary'/></label>
                            <div class="input-group" style="max-width:200px;">
                                <input type="number" name="salary" value="${resumeDto.salary!}"
                                       class="form-control" min="0"/>
                                <span class="input-group-text">₸</span>
                            </div>
                        </div>
                        <div class="mb-4">
                            <div class="form-check">
                                <input type="checkbox" name="active" class="form-check-input" id="activeCheck"
                                       <#if resumeDto.active?? && resumeDto.active>checked</#if>/>
                                <label class="form-check-label" for="activeCheck">
                                    <@spring.message 'resume.active'/>
                                </label>
                            </div>
                        </div>
                        <div class="d-flex gap-2">
                            <button type="submit" class="btn btn-primary">
                                <@spring.message 'resume.form.submit'/>
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