--liquibase formatted sql

--changeset ayushchenko:1
CREATE TABLE IF NOT EXISTS author
(
    id            BIGSERIAL PRIMARY KEY,
    author_name   VARCHAR(255) UNIQUE NOT NULL
);

--changeset ayushchenko:2
CREATE TABLE IF NOT EXISTS book
(
    id       BIGSERIAL PRIMARY KEY,
    title         VARCHAR(255) NOT NULL,
    author_id     BIGINT NOT NULL,
    price         DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (author_id) REFERENCES author (id) ON UPDATE CASCADE
);
