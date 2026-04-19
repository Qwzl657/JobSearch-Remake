<#import "../layout/base.ftlh" as layout>
<@layout.base>
    <div class="card mx-auto mt-4" style="max-width: 600px;">
        <div class="card-body">
            <h3 class="mb-4">Редактирование профиля</h3>
            <form action="/profile/edit" method="post">
                <div class="mb-3">
                    <label class="form-label">Имя</label>
                    <input type="text" class="form-control" name="name" value="${user.name}"/>
                </div>
                <div class="mb-3">
                    <label class="form-label">Фамилия</label>
                    <input type="text" class="form-control" name="surname" value="${user.surname}"/>
                </div>
                <div class="mb-3">
                    <label class="form-label">Возраст</label>
                    <input type="number" class="form-control" name="age" value="${user.age}"/>
                </div>
                <div class="mb-3">
                    <label class="form-label">Телефон</label>
                    <input type="text" class="form-control" name="phoneNumber" value="${user.phoneNumber}"/>
                </div>
                <div class="mt-3 d-flex gap-2">
                    <button type="submit" class="btn btn-primary">Сохранить</button>
                    <a href="/profile" class="btn btn-outline-secondary">Отмена</a>
                </div>
            </form>
        </div>
    </div>
</@layout.base>