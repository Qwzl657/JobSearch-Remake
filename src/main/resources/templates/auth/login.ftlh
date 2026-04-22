<#import "../layout/base.ftlh" as layout>
<@layout.base>
    <div class="card m-3">
        <div class="row g-0 d-flex align-items-center">
            <div class="col-lg-4 d-none d-lg-flex">
                <img src="https://mdbootstrap.com/img/new/ecommerce/vertical/004.jpg"
                     class="w-100 rounded" alt="login image"/>
            </div>
            <div class="col-lg-8">
                <div class="card-body py-5 px-md-5">
                    <h3 class="mb-4">Вход</h3>
                    <#if RequestParameters.error??>
                        <div class="alert alert-danger">Неверный email или пароль</div>
                    </#if>
                    <form action="/auth/login" method="post">
                        <div class="mb-3">
                            <label class="form-label">Email</label>
                            <input type="email" class="form-control" name="username" required/>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Пароль</label>
                            <input type="password" class="form-control" name="password" required/>
                        </div>
                        <button type="submit" class="btn btn-primary w-100">Войти</button>
                        <p class="mt-3 text-center">
                            Нет аккаунта? <a href="/auth/register">Зарегистрироваться</a>
                        </p>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@layout.base>