create schema todo;

CREATE TABLE event (
    id BIGSERIAL PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL,
    text TEXT NOT NULL,
    local_date_time TIMESTAMP NOT NULL
);