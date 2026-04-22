<#import "../layout/base.ftlh" as layout>
<#import "/spring.ftl" as spring>
<@layout.base>
    <div class="card mx-auto mt-4" style="max-width: 600px;">
        <div class="card-body">
            <h3 class="mb-4">Регистрация</h3>
            <form action="/auth/register" method="post">
                <div class="mb-3">
                    <label class="form-label">Имя</label>
                    <@spring.formInput "userDto.name" 'class="form-control"'/>
                    <@spring.showErrors "<br>" "text-danger small"/>
                </div>
                <div class="mb-3">
                    <label class="form-label">Фамилия</label>
                    <@spring.formInput "userDto.surname" 'class="form-control"'/>
                    <@spring.showErrors "<br>" "text-danger small"/>
                </div>
                <div class="mb-3">
                    <label class="form-label">Возраст</label>
                    <@spring.formInput "userDto.age" 'class="form-control" type="number"'/>
                    <@spring.showErrors "<br>" "text-danger small"/>
                </div>
                <div class="mb-3">
                    <label class="form-label">Email</label>
                    <@spring.formInput "userDto.email" 'class="form-control" type="email"'/>
                    <@spring.showErrors "<br>" "text-danger small"/>
                </div>
                <div class="mb-3">
                    <label class="form-label">Пароль</label>
                    <@spring.formInput "userDto.password" 'class="form-control" type="password"'/>
                    <@spring.showErrors "<br>" "text-danger small"/>
                </div>
                <div class="mb-3">
                    <label class="form-label">Телефон</label>
                    <@spring.formInput "userDto.phoneNumber" 'class="form-control"'/>
                    <@spring.showErrors "<br>" "text-danger small"/>
                </div>
                <div class="mb-3">
                    <label class="form-label">Тип аккаунта</label>
                    <select class="form-select" name="accountType" id="accountType">
                        <option value="APPLICANT">Соискатель</option>
                        <option value="EMPLOYER">Работодатель</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary w-100">Зарегистрироваться</button>
                <p class="mt-3 text-center">
                    Уже есть аккаунт? <a href="/auth/login">Войти</a>
                </p>
            </form>
        </div>
    </div>
</@layout.base>