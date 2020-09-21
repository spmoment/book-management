CREATE DATABASE book_db;

\c book_db;

CREATE TABLE authors
(
    id            SERIAL PRIMARY KEY,
    first_name    CHARACTER VARYING(50),
    last_name     CHARACTER VARYING(50),
    year_of_birth DATE
);

CREATE TABLE books
(
    id              SERIAL PRIMARY KEY,
    title           CHARACTER VARYING(50),
    year_publishing DATE,
    annotation      CHARACTER VARYING(1000)
);

CREATE TABLE books_authors
(
    books_id   SMALLINT,
    authors_id SMALLINT,
    PRIMARY KEY (books_id, authors_id),
    FOREIGN KEY (books_id) REFERENCES books (id),
    FOREIGN KEY (authors_id) REFERENCES authors (id)
);