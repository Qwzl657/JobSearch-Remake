'use strict';

window.addEventListener('load', function () {


    document.getElementById('add-work-btn').addEventListener('click', function () {
        addWorkBlock();
    });

    document.getElementById('add-edu-btn').addEventListener('click', function () {
        addEduBlock();
    });

    document.getElementById('save-btn').addEventListener('click', function () {
        saveResume();
    });

});

function addWorkBlock() {
    const container = document.getElementById('work-container');

    const block = document.createElement('div');
    block.classList.add('border', 'rounded', 'p-3', 'mb-3', 'bg-light');

    block.innerHTML =
        '<div class="row g-2">' +
        '<div class="col-md-6">' +
        '<label class="form-label">Компания</label>' +
        '<input type="text" class="form-control work-company" placeholder="Название компании"/>' +
        '</div>' +
        '<div class="col-md-6">' +
        '<label class="form-label">Должность</label>' +
        '<input type="text" class="form-control work-position" placeholder="Должность"/>' +
        '</div>' +
        '<div class="col-md-4">' +
        '<label class="form-label">Лет опыта</label>' +
        '<input type="number" class="form-control work-years" min="0" placeholder="0"/>' +
        '</div>' +
        '<div class="col-md-8">' +
        '<label class="form-label">Обязанности</label>' +
        '<input type="text" class="form-control work-responsibilities" placeholder="Кратко об обязанностях"/>' +
        '</div>' +
        '<div class="col-12 text-end">' +
        '<button type="button" class="btn btn-outline-danger btn-sm remove-btn">Удалить</button>' +
        '</div>' +
        '</div>';


    block.querySelector('.remove-btn').addEventListener('click', function () {
        block.remove();
    });

    container.append(block);
}

function addEduBlock() {
    const container = document.getElementById('edu-container');

    const block = document.createElement('div');
    block.classList.add('border', 'rounded', 'p-3', 'mb-3', 'bg-light');

    block.innerHTML =
        '<div class="row g-2">' +
        '<div class="col-md-6">' +
        '<label class="form-label">Учебное заведение</label>' +
        '<input type="text" class="form-control edu-institution" placeholder="Название заведения"/>' +
        '</div>' +
        '<div class="col-md-6">' +
        '<label class="form-label">Специальность</label>' +
        '<input type="text" class="form-control edu-program" placeholder="Специальность"/>' +
        '</div>' +
        '<div class="col-md-4">' +
        '<label class="form-label">Степень</label>' +
        '<input type="text" class="form-control edu-degree" placeholder="Бакалавр, Магистр..."/>' +
        '</div>' +
        '<div class="col-12 text-end">' +
        '<button type="button" class="btn btn-outline-danger btn-sm remove-btn">Удалить</button>' +
        '</div>' +
        '</div>';

    block.querySelector('.remove-btn').addEventListener('click', function () {
        block.remove();
    });

    container.append(block);
}

async function saveResume() {

    const name = document.getElementById('name').value;
    const salary = document.getElementById('salary').value;
    const active = document.getElementById('active').checked;

    const workBlocks = document.getElementById('work-container').getElementsByClassName('border');
    const workExperienceInfos = [];
    for (let i = 0; i < workBlocks.length; i++) {
        const block = workBlocks[i];
        workExperienceInfos.push({
            companyName: block.querySelector('.work-company').value,
            position: block.querySelector('.work-position').value,
            years: parseInt(block.querySelector('.work-years').value) || 0,
            responsibilities: block.querySelector('.work-responsibilities').value
        });
    }

    const eduBlocks = document.getElementById('edu-container').getElementsByClassName('border');
    const educationInfos = [];
    for (let i = 0; i < eduBlocks.length; i++) {
        const block = eduBlocks[i];
        educationInfos.push({
            institution: block.querySelector('.edu-institution').value,
            program: block.querySelector('.edu-program').value,
            degree: block.querySelector('.edu-degree').value
        });
    }

    const resumeData = {
        name: name,
        salary: salary ? parseFloat(salary) : null,
        active: active,
        workExperienceInfos: workExperienceInfos,
        educationInfos: educationInfos
    };

    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    const resumeId = document.querySelector('meta[name="_resume_id"]').getAttribute('content');
    const url = resumeId ? '/api/resumes/' + resumeId : '/api/resumes';

    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                [csrfHeader]: csrfToken
            },
            body: JSON.stringify(resumeData)
        });

        if (response.ok) {
            window.location.href = '/profile';
        } else {
            const errorDiv = document.getElementById('form-error');
            errorDiv.classList.remove('d-none');
            errorDiv.innerHTML = 'Ошибка при сохранении. Проверьте данные.';
        }
    } catch (error) {
        const errorDiv = document.getElementById('form-error');
        errorDiv.classList.remove('d-none');
        errorDiv.innerHTML = 'Ошибка соединения с сервером.';
    }
}