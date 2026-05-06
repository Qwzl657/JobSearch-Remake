-- Полномочия и роли
INSERT INTO authorities (authority) VALUES ('FULL'), ('READ_ONLY');

INSERT INTO roles (role, authority_id) VALUES
                                           ('EMPLOYER', (SELECT id FROM authorities WHERE authority = 'FULL')),
                                           ('APPLICANT', (SELECT id FROM authorities WHERE authority = 'FULL'));

-- Типы контактов
INSERT INTO contact_types (type) VALUES
                                     ('Telegram'),
                                     ('Email'),
                                     ('Phone'),
                                     ('LinkedIn'),
                                     ('Facebook');

-- Категории (все сразу, без дублирования)
INSERT INTO categories (name, parent_id) VALUES
                                             ('IT',               NULL),
                                             ('Finance',          NULL),
                                             ('Marketing',        NULL),
                                             ('Java Developer',   1),
                                             ('Python Developer', 1),
                                             ('Frontend Developer', 1),
                                             ('DevOps',           1),
                                             ('Data Science',     1);

-- Пользователи (пароль: "password", зашифрован BCrypt)
-- Соискатели
INSERT INTO users (name, surname, age, email, password, phone_number, avatar, account_type, enabled)
VALUES
    ('Ali',    'Dev',     22, 'ali@mail.com',    '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+996100000001', 'default.png', 'APPLICANT', TRUE),
    ('Ivan',   'Petrov',  25, 'ivan@mail.com',   '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+996100000002', 'default.png', 'APPLICANT', TRUE),
    ('Maria',  'Ivanova', 23, 'maria@mail.com',  '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+996100000003', 'default.png', 'APPLICANT', TRUE),
    ('Sergey', 'Sidorov', 27, 'sergey@mail.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+996100000004', 'default.png', 'APPLICANT', TRUE);

-- Работодатели
INSERT INTO users (name, surname, age, email, password, phone_number, avatar, account_type, enabled)
VALUES
    ('Boss',   'HR', 35, 'boss@mail.com',   '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+996200000001', 'default.png', 'EMPLOYER', TRUE),
    ('Google', 'HR', 30, 'google@mail.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+996200000002', 'default.png', 'EMPLOYER', TRUE),
    ('Apple',  'HR', 28, 'apple@mail.com',  '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+996200000003', 'default.png', 'EMPLOYER', TRUE),
    ('Amazon', 'HR', 35, 'amazon@mail.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+996200000004', 'default.png', 'EMPLOYER', TRUE);

-- 15 вакансий от разных работодателей
INSERT INTO vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_time)
VALUES
    ('Java Developer',      'Backend разработка на Java',       1, 1500, 1, 3,  TRUE, (SELECT id FROM users WHERE email='google@mail.com'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Python Developer',    'ML и data science разработка',     1, 1800, 2, 5,  TRUE, (SELECT id FROM users WHERE email='google@mail.com'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Frontend Developer',  'React и Vue разработка',           1, 1200, 0, 2,  TRUE, (SELECT id FROM users WHERE email='apple@mail.com'),  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('iOS Developer',       'Swift разработка под iOS',         1, 2000, 2, 5,  TRUE, (SELECT id FROM users WHERE email='apple@mail.com'),  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Android Developer',   'Kotlin разработка под Android',    1, 1700, 1, 3,  TRUE, (SELECT id FROM users WHERE email='amazon@mail.com'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('DevOps Engineer',     'CI/CD, Docker, Kubernetes',        1, 2200, 3, 6,  TRUE, (SELECT id FROM users WHERE email='amazon@mail.com'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('QA Engineer',         'Ручное и авто тестирование',       1, 1000, 0, 2,  TRUE, (SELECT id FROM users WHERE email='boss@mail.com'),   CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Data Analyst',        'Анализ и визуализация данных',     1, 1600, 1, 3,  TRUE, (SELECT id FROM users WHERE email='boss@mail.com'),   CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('UI/UX Designer',      'Дизайн интерфейсов в Figma',       1, 1300, 1, 3,  TRUE, (SELECT id FROM users WHERE email='google@mail.com'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Project Manager',     'Управление командой разработки',   1, 1900, 3, 7,  TRUE, (SELECT id FROM users WHERE email='apple@mail.com'),  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('System Administrator','Администрирование Linux серверов', 1, 1100, 1, 4,  TRUE, (SELECT id FROM users WHERE email='amazon@mail.com'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Security Engineer',   'Информационная безопасность',      1, 2500, 3, 8,  TRUE, (SELECT id FROM users WHERE email='boss@mail.com'),   CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('ML Engineer',         'Машинное обучение и нейросети',    1, 2300, 2, 5,  TRUE, (SELECT id FROM users WHERE email='google@mail.com'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Fullstack Developer', 'Spring Boot + React разработка',   1, 1800, 2, 5,  TRUE, (SELECT id FROM users WHERE email='apple@mail.com'),  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Tech Lead',           'Тех лид команды бэкенда',          1, 3000, 5, 10, TRUE, (SELECT id FROM users WHERE email='amazon@mail.com'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 15 резюме от разных соискателей
INSERT INTO resumes (applicant_id, name, category_id, salary, is_active, created_date, update_time)
VALUES
    ((SELECT id FROM users WHERE email='ali@mail.com'),    'Java Developer',     1, 1200, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE email='ali@mail.com'),    'Backend Developer',  1, 1400, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE email='ali@mail.com'),    'Fullstack Developer',1, 1500, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE email='ali@mail.com'),    'iOS Developer',      1, 1700, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE email='ivan@mail.com'),   'Python Developer',   1, 1300, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE email='ivan@mail.com'),   'Data Scientist',     1, 1600, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE email='ivan@mail.com'),   'ML Engineer',        1, 2000, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE email='ivan@mail.com'),   'Tech Lead',          1, 2500, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE email='maria@mail.com'),  'Frontend Developer', 1, 1000, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE email='maria@mail.com'),  'UI Designer',        1, 900,  TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE email='maria@mail.com'),  'QA Engineer',        1, 800,  TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE email='maria@mail.com'),  'Project Manager',    1, 1600, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE email='sergey@mail.com'), 'DevOps Engineer',    1, 1800, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE email='sergey@mail.com'), 'System Admin',       1, 1100, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE email='sergey@mail.com'), 'Android Developer',  1, 1400, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

--Несколько откликов для демонстрации связей
INSERT INTO responded_applicants (resume_id, vacancy_id, confirmation)
VALUES
    ((SELECT id FROM resumes WHERE name='Java Developer'     LIMIT 1), (SELECT id FROM vacancies WHERE name='Java Developer'     LIMIT 1), FALSE),
    ((SELECT id FROM resumes WHERE name='Python Developer'   LIMIT 1), (SELECT id FROM vacancies WHERE name='Python Developer'   LIMIT 1), FALSE),
    ((SELECT id FROM resumes WHERE name='Frontend Developer' LIMIT 1), (SELECT id FROM vacancies WHERE name='Frontend Developer' LIMIT 1), TRUE);