CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(100),
                       surname VARCHAR(100),
                       age INT,
                       email VARCHAR(100) UNIQUE,
                       password VARCHAR(100),
                       phone_number VARCHAR(50),
                       avatar VARCHAR(255),
                       account_type VARCHAR(20)
);

CREATE TABLE resumes (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         user_id BIGINT,
                         title VARCHAR(100),
                         category VARCHAR(50)
);

CREATE TABLE vacancies (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           user_id BIGINT,
                           title VARCHAR(100),
                           category VARCHAR(50)
);

CREATE TABLE responses (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           user_id BIGINT,
                           vacancy_id BIGINT
);

INSERT INTO users (name, surname, age, email, password, phone_number, avatar, account_type)
VALUES
    ('Ali', 'Dev', 22, 'ali@mail.com', '123', '123', 'avatar1.png', 'APPLICANT'),
    ('Boss', 'HR', 35, 'boss@mail.com', '123', '999', 'avatar2.png', 'EMPLOYER');

INSERT INTO resumes (user_id, title, category)
VALUES
    (1, 'Java Dev', 'IT'),
    (1, 'Backend Dev', 'IT');

INSERT INTO vacancies (user_id, title, category)
VALUES
    (2, 'Java Junior', 'IT'),
    (2, 'Backend Intern', 'IT');

INSERT INTO responses (user_id, vacancy_id)
VALUES
    (1, 1);