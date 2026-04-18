-- Полномочия
INSERT INTO authorities (authority) VALUES ('FULL'), ('READ_ONLY');

-- Роли
INSERT INTO roles (role, authority_id)
VALUES
    ('EMPLOYER', (SELECT id FROM authorities WHERE authority = 'FULL')),
    ('APPLICANT', (SELECT id FROM authorities WHERE authority = 'FULL'));

-- Удаляем зависимые записи сначала
DELETE FROM responded_applicants;
DELETE FROM contact_info;
DELETE FROM education_info;
DELETE FROM work_experience_info;
DELETE FROM resumes;
DELETE FROM vacancies;
DELETE FROM users;

-- Новые пользователи с зашифрованным паролем (пароль: "password")
INSERT INTO users (name, surname, age, email, password, phone_number, avatar, account_type, enabled, role_id)
VALUES
    ('Ali', 'Dev', 22, 'ali@mail.com',
     '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2',
     '123', 'avatar1.png', 'APPLICANT', TRUE,
     (SELECT id FROM roles WHERE role = 'APPLICANT')),

    ('Boss', 'HR', 35, 'boss@mail.com',
     '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2',
     '999', 'avatar2.png', 'EMPLOYER', TRUE,
     (SELECT id FROM roles WHERE role = 'EMPLOYER'));