CREATE DATABASE book_db;

CREATE TABLE authors
    (id SERIAL PRIMARY KEY,
    first_name CHARACTER VARYING(30),
    last_name CHARACTER VARYING(30),
    year_of_birth SMALLINT);

CREATE TABLE books
    (id SERIAL PRIMARY KEY,
    title CHARACTER VARYING(30),
    year_publishing SMALLINT,
    annotation CHARACTER VARYING(30));

CREATE TABLE books_authors
    (books_id SERIAL,
    authors_id SERIAL,
    PRIMARY KEY(books_id, authors_id),
    FOREIGN KEY (books_id) REFERENCES books (id),
    FOREIGN KEY (authors_id) REFERENCES authors (id));