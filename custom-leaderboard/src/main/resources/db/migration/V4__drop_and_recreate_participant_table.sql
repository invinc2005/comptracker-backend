drop  table  participant;

CREATE TABLE participant (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    score INT,
    competition_id BIGINT NOT NULL,
    CONSTRAINT fk_competition
        FOREIGN KEY (competition_id) REFERENCES competition(id)
        ON DELETE CASCADE
);
