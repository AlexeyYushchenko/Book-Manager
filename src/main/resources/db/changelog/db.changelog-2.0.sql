--liquibase formatted sql

--changeset ayushchenko:1
ALTER TABLE author
RENAME COLUMN author_name TO name;