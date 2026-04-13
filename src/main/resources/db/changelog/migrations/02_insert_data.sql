-- CATEGORIES
INSERT INTO categories (name) VALUES ('IT'), ('Finance'), ('Marketing');

-- USERS
INSERT INTO users (name, surname, age, email, password, phone_number, avatar, account_type)
VALUES
    ('Ali', 'Dev', 22, 'ali@mail.com', '123', '123', 'avatar1.png', 'APPLICANT'),
    ('Boss', 'HR', 35, 'boss@mail.com', '123', '999', 'avatar2.png', 'EMPLOYER');

-- RESUMES
INSERT INTO resumes (applicant_id, name, category_id, salary, is_active, created_date, update_time)
VALUES
    (1, 'Java Dev', 1, 1000, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (1, 'Backend Dev', 1, 1200, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- VACANCIES
INSERT INTO vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_time)
VALUES
    ('Java Junior', 'Java developer position', 1, 800, 0, 1, TRUE, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Backend Intern', 'Intern backend position', 1, 500, 0, 1, TRUE, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- RESPONSES
INSERT INTO responded_applicants (resume_id, vacancy_id, confirmation)
VALUES (1, 1, false);