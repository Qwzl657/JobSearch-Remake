CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(100),
                       email VARCHAR(100) UNIQUE,
                       phone VARCHAR(50),
                       role VARCHAR(20)
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