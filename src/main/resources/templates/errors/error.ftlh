<#import "../layout/base.ftlh" as layout>
<@layout.base>
    <div class="text-center mt-5">
        <h1 class="display-1 fw-bold">${status}</h1>
        <h2 class="mb-4">${reason}</h2>
        <#if details??>
            <div class="card mx-auto" style="max-width: 600px;">
                <div class="card-body text-start">
                    <p><strong>URL:</strong> ${details.requestURL!""}</p>
                    <p><strong>Host:</strong> ${details.serverName!""}</p>
                    <p><strong>Port:</strong> ${details.serverPort!""}</p>
                    <p><strong>Time:</strong> ${.now}</p>
                </div>
            </div>
        </#if>
        <a href="/" class="btn btn-primary mt-3">На главную</a>
    </div>
</@layout.base>