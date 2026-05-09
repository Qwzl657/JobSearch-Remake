<#import "/spring.ftl" as spring>
<#import "/layout/base.ftlh" as layout>

<@layout.layout>

    <div class="row justify-content-center">
        <div class="col-md-6">
            <nav aria-label="breadcrumb" class="mb-3">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="/profile"><@spring.message 'nav.profile'/></a></li>
                    <li class="breadcrumb-item active"><@spring.message 'profile.edit.title'/></li>
                </ol>
            </nav>
            <div class="card shadow-sm">
                <div class="card-header bg-white py-3">
                    <h5 class="mb-0 fw-bold"><@spring.message 'profile.edit.title'/></h5>
                </div>
                <div class="card-body p-4">
                    <form method="post" action="/profile/edit" enctype="multipart/form-data">
                        <#if _csrf??>
                            <input type="hidden"
                                   name="${(_csrf.parameterName)!'csrf-param-name'}"
                                   value="${(_csrf.token)!'csrf-token'}"/>
                        </#if>
                        <div class="row g-3 mb-3">
                            <div class="col-md-6">
                                <label class="form-label"><@spring.message 'profile.name'/></label>
                                <input type="text" name="name" value="${user.name!}"
                                       class="form-control" required/>
                            </div>
                            <div class="col-md-6">
                                <label class="form-label"><@spring.message 'profile.surname'/></label>
                                <input type="text" name="surname" value="${user.surname!}"
                                       class="form-control" required/>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label class="form-label"><@spring.message 'profile.age'/></label>
                            <input type="number" name="age" value="${user.age!}"
                                   class="form-control" style="max-width:120px;"/>
                        </div>
                        <div class="mb-3">
                            <label class="form-label"><@spring.message 'profile.phone'/></label>
                            <input type="text" name="phoneNumber" value="${user.phoneNumber!}"
                                   class="form-control"/>
                        </div>
                        <div class="mb-4">
                            <label class="form-label"><@spring.message 'profile.avatar'/></label>
                            <input type="file" name="avatarFile" class="form-control" accept="image/*"/>
                        </div>
                        <div class="d-flex gap-2">
                            <button type="submit" class="btn btn-primary">
                                <@spring.message 'profile.edit.submit'/>
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