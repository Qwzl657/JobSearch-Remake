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
                         applicant_id BIGINT,
                         name VARCHAR(100),
                         category_id INT,
                         salary DOUBLE,
                         is_active BOOLEAN,
                         created_date DATE,
                         update_time DATE
);

CREATE TABLE vacancies (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(100),
                           description TEXT,
                           category_id INT,
                           salary DOUBLE,
                           exp_from INT,
                           exp_to INT,
                           is_active BOOLEAN,
                           author_id BIGINT,
                           created_date DATE,
                           update_time DATE
);

CREATE TABLE responses (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           user_id BIGINT,
                           vacancy_id BIGINT
);