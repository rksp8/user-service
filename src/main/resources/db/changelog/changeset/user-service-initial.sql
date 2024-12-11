CREATE TABLE users
(
    id         SERIAL       PRIMARY KEY,
    username   VARCHAR(255) NOT NULL,
    password   VARCHAR(255),
    role       VARCHAR(255) NOT NULL,
    provider   VARCHAR(255) NOT NULL,
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);