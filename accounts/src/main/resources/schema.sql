-- This file should be used to run schema on the db

CREATE TABLE IF NOT EXISTS ACCOUNTS (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(200),
    EMAIL VARCHAR(50),
    BRANCH VARCHAR(50),
    CREATED_AT DATE,
    UPDATED_AT DATE
);