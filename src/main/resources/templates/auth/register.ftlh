<#import "../layout/base.ftlh" as layout>
<@layout.base>
    <div class="card mx-auto mt-4" style="max-width: 600px;">
        <div class="card-body">
            <h3 class="mb-4">Регистрация</h3>
            <form action="/auth/register" method="post">
                <div class="mb-3">
                    <label class="form-label">Имя</label>
                    <input type="text" class="form-control" name="name" required/>
                </div>
                <div class="mb-3">
                    <label class="form-label">Фамилия</label>
                    <input type="text" class="form-control" name="surname" required/>
                </div>
                <div class="mb-3">
                    <label class="form-label">Возраст</label>
                    <input type="number" class="form-control" name="age" required/>
                </div>
                <div class="mb-3">
                    <label class="form-label">Email</label>
                    <input type="email" class="form-control" name="email" required/>
                </div>
                <div class="mb-3">
                    <label class="form-label">Пароль</label>
                    <input type="password" class="form-control" name="password" required/>
                </div>
                <div class="mb-3">
                    <label class="form-label">Телефон</label>
                    <input type="text" class="form-control" name="phoneNumber" required/>
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