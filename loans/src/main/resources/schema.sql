-- SCHEMA.SQL SPECIAL FILE

CREATE TABLE IF NOT EXISTS LOANS (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    LOAN_TYPE VARCHAR(100) NOT NULL,
    LOAN_AMOUNT DECIMAL NOT NULL,
    START_DATE DATE,
    LOAN_TERM INT NOT NULL,
    ACCOUNT_ID INT,
    CREATED_AT DATE,
    UPDATED_AT DATE
);