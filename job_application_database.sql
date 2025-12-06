DROP DATABASE IF EXISTS job_application_db;
CREATE DATABASE job_application_db;

USE job_application_db;

CREATE TABLE IF NOT EXISTS applications {
    application_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    company VARCHAR(255) NOT NULL,
    summary TEXT,
    application_link VARCHAR(512),
    date_sent DATE,
    elapsed_time INT,
    status VARCHAR(100),
    interview_status VARCHAR(100),
    email VARCHAR(255)
};