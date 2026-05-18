<#import "/spring.ftl" as spring>
<#import "/layout/base.ftlh" as layout>

<@layout.layout>

    <div class="row justify-content-center mt-5">
        <div class="col-md-6 text-center">
            <h1 class="display-1 fw-bold text-danger">${status!500}</h1>
            <h4 class="mb-3">
                <#if status?? && status == 404>
                    <@spring.message 'error.404.title'/>
                <#else>
                    <@spring.message 'error.500.title'/>
                </#if>
            </h4>
            <#if reason?has_content>
                <p class="text-muted">${reason}</p>
            </#if>
            <a href="/" class="btn btn-primary mt-3">
                <@spring.message 'error.back'/>
            </a>
        </div>
    </div>

</@layout.layout>