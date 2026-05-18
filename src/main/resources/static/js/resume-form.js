'use strict';

window.addEventListener('load', function () {

    const i18n = document.getElementById('i18n-data');
    const labels = {
        workCompany:          i18n.getAttribute('data-work-company'),
        workPosition:         i18n.getAttribute('data-work-position'),
        workYears:            i18n.getAttribute('data-work-years'),
        workResponsibilities: i18n.getAttribute('data-work-responsibilities'),
        workRemove:           i18n.getAttribute('data-work-remove'),
        eduInstitution:       i18n.getAttribute('data-edu-institution'),
        eduProgram:           i18n.getAttribute('data-edu-program'),
        eduDegree:            i18n.getAttribute('data-edu-degree'),
        eduRemove:            i18n.getAttribute('data-edu-remove'),
        errorSave:            i18n.getAttribute('data-error-save'),
        errorConnection:      i18n.getAttribute('data-error-connection')
    };

    const existingWork = JSON.parse(
        document.getElementById('existing-work-data').textContent || '[]'
    );
    const existingEdu = JSON.parse(
        document.getElementById('existing-edu-data').textContent || '[]'
    );

    existingWork.forEach(function (item) {
        addWorkBlock(item, labels);
    });
    existingEdu.forEach(function (item) {
        addEduBlock(item, labels);
    });

    document.getElementById('add-work-btn').addEventListener('click', function () {
        addWorkBlock(null, labels);
    });

    document.getElementById('add-edu-btn').addEventListener('click', function () {
        addEduBlock(null, labels);
    });

    document.getElementById('save-btn').addEventListener('click', function () {
        saveResume(labels);
    });
});

function addWorkBlock(data, labels) {
    const container = document.getElementById('work-container');
    const block = document.createElement('div');
    block.classList.add('border', 'rounded', 'p-3', 'mb-3', 'bg-light');

    block.innerHTML =
        '<div class="row g-2">' +
        '<div class="col-md-6">' +
        '<label class="form-label">' + labels.workCompany + '</label>' +
        '<input type="text" class="form-control work-company" value=""/>' +
        '</div>' +
        '<div class="col-md-6">' +
        '<label class="form-label">' + labels.workPosition + '</label>' +
        '<input type="text" class="form-control work-position" value=""/>' +
        '</div>' +
        '<div class="col-md-4">' +
        '<label class="form-label">' + labels.workYears + '</label>' +
        '<input type="number" class="form-control work-years" min="0" value=""/>' +
        '</div>' +
        '<div class="col-md-8">' +
        '<label class="form-label">' + labels.workResponsibilities + '</label>' +
        '<input type="text" class="form-control work-responsibilities" value=""/>' +
        '</div>' +
        '<div class="col-12 text-end">' +
        '<button type="button" class="btn btn-outline-danger btn-sm remove-btn">' +
        labels.workRemove +
        '</button>' +
        '</div>' +
        '</div>';

    if (data) {
        block.querySelector('.work-company').value        = data.companyName || '';
        block.querySelector('.work-position').value       = data.position || '';
        block.querySelector('.work-years').value          = data.years || 0;
        block.querySelector('.work-responsibilities').value = data.responsibilities || '';
    }

    block.querySelector('.remove-btn').addEventListener('click', function () {
        block.remove();
    });

    container.append(block);
}

function addEduBlock(data, labels) {
    const container = document.getElementById('edu-container');
    const block = document.createElement('div');
    block.classList.add('border', 'rounded', 'p-3', 'mb-3', 'bg-light');

    block.innerHTML =
        '<div class="row g-2">' +
        '<div class="col-md-6">' +
        '<label class="form-label">' + labels.eduInstitution + '</label>' +
        '<input type="text" class="form-control edu-institution" value=""/>' +
        '</div>' +
        '<div class="col-md-6">' +
        '<label class="form-label">' + labels.eduProgram + '</label>' +
        '<input type="text" class="form-control edu-program" value=""/>' +
        '</div>' +
        '<div class="col-md-4">' +
        '<label class="form-label">' + labels.eduDegree + '</label>' +
        '<input type="text" class="form-control edu-degree" value=""/>' +
        '</div>' +
        '<div class="col-12 text-end">' +
        '<button type="button" class="btn btn-outline-danger btn-sm remove-btn">' +
        labels.eduRemove +
        '</button>' +
        '</div>' +
        '</div>';

    if (data) {
        block.querySelector('.edu-institution').value = data.institution || '';
        block.querySelector('.edu-program').value     = data.program || '';
        block.querySelector('.edu-degree').value      = data.degree || '';
    }

    block.querySelector('.remove-btn').addEventListener('click', function () {
        block.remove();
    });

    container.append(block);
}

async function saveResume(labels) {
    const name   = document.getElementById('name').value;
    const salary = document.getElementById('salary').value;
    const active = document.getElementById('active').checked;

    const workBlocks = document.getElementById('work-container')
        .querySelectorAll('.border');
    const workExperienceInfos = [];
    workBlocks.forEach(function (block) {
        workExperienceInfos.push({
            companyName:      block.querySelector('.work-company').value,
            position:         block.querySelector('.work-position').value,
            years:            parseInt(block.querySelector('.work-years').value) || 0,
            responsibilities: block.querySelector('.work-responsibilities').value
        });
    });

    const eduBlocks = document.getElementById('edu-container')
        .querySelectorAll('.border');
    const educationInfos = [];
    eduBlocks.forEach(function (block) {
        educationInfos.push({
            institution: block.querySelector('.edu-institution').value,
            program:     block.querySelector('.edu-program').value,
            degree:      block.querySelector('.edu-degree').value
        });
    });

    const resumeData = {
        name:                name,
        salary:              salary ? parseFloat(salary) : null,
        active:              active,
        workExperienceInfos: workExperienceInfos,
        educationInfos:      educationInfos
    };

    const csrfToken  = document.querySelector('meta[name="_csrf"]')
        .getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]')
        .getAttribute('content');
    const resumeId   = document.querySelector('meta[name="_resume_id"]')
        .getAttribute('content');

    const method = resumeId ? 'PUT' : 'POST';
    const url    = resumeId ? '/api/resumes/' + resumeId : '/api/resumes';

    const errorDiv = document.getElementById('form-error');

    try {
        const response = await fetch(url, {
            method:  method,
            headers: {
                'Content-Type': 'application/json',
                [csrfHeader]:   csrfToken
            },
            body: JSON.stringify(resumeData)
        });

        if (response.ok) {
            window.location.href = '/profile';
        } else {
            errorDiv.classList.remove('d-none');
            errorDiv.textContent = labels.errorSave;
        }
    } catch (error) {
        errorDiv.classList.remove('d-none');
        errorDiv.textContent = labels.errorConnection;
    }
}