<#import "/spring.ftl" as spring>
<#import "/layout/base.ftlh" as layout>

<@layout.layout>

    <meta name="_csrf" content="${(_csrf.token)!''}"/>
    <meta name="_csrf_header" content="${(_csrf.headerName)!'X-CSRF-TOKEN'}"/>
    <meta name="_resume_id" content="${resumeDto.id!}"/>

    <div id="i18n-data"
         data-work-company="<@spring.message 'resume.work.company'/>"
         data-work-position="<@spring.message 'resume.work.position'/>"
         data-work-years="<@spring.message 'resume.work.years'/>"
         data-work-responsibilities="<@spring.message 'resume.work.responsibilities'/>"
         data-work-remove="<@spring.message 'resume.work.remove'/>"
         data-edu-institution="<@spring.message 'resume.edu.institution'/>"
         data-edu-program="<@spring.message 'resume.edu.program'/>"
         data-edu-degree="<@spring.message 'resume.edu.degree'/>"
         data-edu-remove="<@spring.message 'resume.edu.remove'/>"
         data-error-save="<@spring.message 'resume.form.error.save'/>"
         data-error-connection="<@spring.message 'resume.form.error.connection'/>"
         style="display:none;">
    </div>

    <script id="existing-work-data" type="application/json">
        [<#if resumeDto.workExperienceInfos?has_content>
        <#list resumeDto.workExperienceInfos as w>
            {
                "companyName": "${w.companyName!?js_string}",
                "position": "${w.position!?js_string}",
                "years": ${w.years!0},
                "responsibilities": "${w.responsibilities!?js_string}"
            }<#if w?has_next>,</#if>
        </#list>
        </#if>]
    </script>

    <script id="existing-edu-data" type="application/json">
        [<#if resumeDto.educationInfos?has_content>
        <#list resumeDto.educationInfos as e>
            {
                "institution": "${e.institution!?js_string}",
                "program": "${e.program!?js_string}",
                "degree": "${e.degree!?js_string}"
            }<#if e?has_next>,</#if>
        </#list>
        </#if>]
    </script>

    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card shadow-sm">
                <div class="card-header bg-white py-3">
                    <h5 class="mb-0 fw-bold">
                        <#if resumeDto.id??>
                            <@spring.message 'resume.form.title.edit'/>
                        <#else>
                            <@spring.message 'resume.form.title.create'/>
                        </#if>
                    </h5>
                </div>
                <div class="card-body p-4">

                    <div id="form-error" class="alert alert-danger d-none"></div>

                    <div class="mb-3">
                        <label class="form-label">
                            <@spring.message 'resume.name'/>
                        </label>
                        <input type="text" id="name" value="${resumeDto.name!}"
                               class="form-control" required/>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">
                            <@spring.message 'resume.salary'/>
                        </label>
                        <div class="input-group" style="max-width:200px;">
                            <input type="number" id="salary"
                                   value="${resumeDto.salary!}"
                                   class="form-control" min="0"/>
                            <span class="input-group-text">₸</span>
                        </div>
                    </div>

                    <div class="mb-4">
                        <div class="form-check">
                            <input type="checkbox" id="active" class="form-check-input"
                                   <#if resumeDto.active>checked</#if>/>
                            <label class="form-check-label" for="active">
                                <@spring.message 'resume.active'/>
                            </label>
                        </div>
                    </div>

                    <hr/>
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h6 class="fw-bold mb-0">
                            <@spring.message 'resume.work.title'/>
                        </h6>
                        <button type="button" id="add-work-btn"
                                class="btn btn-outline-primary btn-sm">
                            + <@spring.message 'resume.work.add'/>
                        </button>
                    </div>
                    <div id="work-container"></div>

                    <hr/>
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h6 class="fw-bold mb-0">
                            <@spring.message 'resume.edu.title'/>
                        </h6>
                        <button type="button" id="add-edu-btn"
                                class="btn btn-outline-primary btn-sm">
                            + <@spring.message 'resume.edu.add'/>
                        </button>
                    </div>
                    <div id="edu-container"></div>

                    <hr/>
                    <div class="d-flex gap-2 mt-3">
                        <button type="button" id="save-btn" class="btn btn-primary">
                            <@spring.message 'resume.form.submit'/>
                        </button>
                        <a href="/profile" class="btn btn-outline-secondary">
                            <@spring.message 'profile.edit.cancel'/>
                        </a>
                    </div>

                </div>
            </div>
        </div>
    </div>

    <script src="/js/resume-form.js"></script>

</@layout.layout>