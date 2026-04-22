-- Дополнительные пользователи-работодатели
INSERT INTO users (name, surname, age, email, password, phone_number, avatar, account_type, enabled, role_id)
VALUES
    ('Google', 'HR', 30, 'google@mail.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '111', 'default.png', 'EMPLOYER', TRUE, (SELECT id FROM roles WHERE role = 'EMPLOYER')),
    ('Apple', 'HR', 28, 'apple@mail.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '222', 'default.png', 'EMPLOYER', TRUE, (SELECT id FROM roles WHERE role = 'EMPLOYER')),
    ('Amazon', 'HR', 35, 'amazon@mail.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '333', 'default.png', 'EMPLOYER', TRUE, (SELECT id FROM roles WHERE role = 'EMPLOYER'));

-- Дополнительные соискатели
INSERT INTO users (name, surname, age, email, password, phone_number, avatar, account_type, enabled, role_id)
VALUES
    ('Ivan', 'Petrov', 25, 'ivan@mail.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '444', 'default.png', 'APPLICANT', TRUE, (SELECT id FROM roles WHERE role = 'APPLICANT')),
    ('Maria', 'Ivanova', 23, 'maria@mail.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '555', 'default.png', 'APPLICANT', TRUE, (SELECT id FROM roles WHERE role = 'APPLICANT')),
    ('Sergey', 'Sidorov', 27, 'sergey@mail.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '666', 'default.png', 'APPLICANT', TRUE, (SELECT id FROM roles WHERE role = 'APPLICANT'));

-- 15 вакансий
INSERT INTO vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_time)
VALUES
    ('Java Developer', 'Backend разработка', 1, 1500, 1, 3, TRUE, (SELECT id FROM users WHERE email='google@mail.com'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Python Developer', 'ML разработка', 1, 1800, 2, 5, TRUE, (SELECT id FROM users WHERE email='google@mail.com'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Frontend Developer', 'React разработка', 1, 1200, 0, 2, TRUE, (SELECT id FROM users WHERE email='apple@mail.com'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('iOS Developer', 'Swift разработка', 1, 2000, 2, 5, TRUE, (SELECT id FROM users WHERE email='apple@mail.com'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Android Developer', 'Kotlin разработка', 1, 1700, 1, 3, TRUE, (SELECT id FROM users WHERE email='amazon@mail.com'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('DevOps Engineer', 'CI/CD настройка', 1, 2200, 3, 6, TRUE, (SELECT id FROM users WHERE email='amazon@mail.com'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('QA Engineer', 'Тестирование', 1, 1000, 0, 2, TRUE, (SELECT id FROM users WHERE email='boss@mail.com'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Data Analyst', 'Анализ данных', 1, 1600, 1, 3, TRUE, (SELECT id FROM users WHERE email='boss@mail.com'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('UI/UX Designer', 'Дизайн интерфейсов', 1, 1300, 1, 3, TRUE, (SELECT id FROM users WHERE email='google@mail.com'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Project Manager', 'Управление проектами', 1, 1900, 3, 7, TRUE, (SELECT id FROM users WHERE email='apple@mail.com'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('System Administrator', 'Администрирование', 1, 1100, 1, 4, TRUE, (SELECT id FROM users WHERE email='amazon@mail.com'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Security Engineer', 'Информационная безопасность', 1, 2500, 3, 8, TRUE, (SELECT id FROM users WHERE email='boss@mail.com'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('ML Engineer', 'Машинное обучение', 1, 2300, 2, 5, TRUE, (SELECT id FROM users WHERE email='google@mail.com'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Fullstack Developer', 'Full stack разработка', 1, 1800, 2, 5, TRUE, (SELECT id FROM users WHERE email='apple@mail.com'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Tech Lead', 'Тех лид команды', 1, 3000, 5, 10, TRUE, (SELECT id FROM users WHERE email='amazon@mail.com'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 15 резюме
INSERT INTO resumes (applicant_id, name, category_id, salary, is_active, created_date, update_time)
VALUES
    ((SELECT id FROM users WHERE email='ali@mail.com'), 'Java Developer', 1, 1200, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE email='ali@mail.com'), 'Backend Developer', 1, 1400, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE email='ivan@mail.com'), 'Python Developer', 1, 1300, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE email='ivan@mail.com'), 'Data Scientist', 1, 1600, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE email='maria@mail.com'), 'Frontend Developer', 1, 1000, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE email='maria@mail.com'), 'UI Designer', 1, 900, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE email='sergey@mail.com'), 'DevOps Engineer', 1, 1800, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE email='sergey@mail.com'), 'System Admin', 1, 1100, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE email='ali@mail.com'), 'Fullstack Developer', 1, 1500, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE email='ivan@mail.com'), 'ML Engineer', 1, 2000, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE email='maria@mail.com'), 'QA Engineer', 1, 800, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE email='sergey@mail.com'), 'Android Developer', 1, 1400, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE email='ali@mail.com'), 'iOS Developer', 1, 1700, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE email='ivan@mail.com'), 'Tech Lead', 1, 2500, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE email='maria@mail.com'), 'Project Manager', 1, 1600, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);