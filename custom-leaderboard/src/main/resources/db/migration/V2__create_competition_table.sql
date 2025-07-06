CREATE TABLE competition (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    description TEXT,
    start_date  DATETIME(6) NOT NULL,
    end_date DATETIME(6) NOT NULL
);
