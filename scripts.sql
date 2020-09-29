CREATE DATABASE book_db;

CREATE TABLE authors
    (id SERIAL PRIMARY KEY,
    first_name CHARACTER VARYING(50),
    last_name CHARACTER VARYING(50),
    year_of_birth DATE);

CREATE TABLE books
    (id SERIAL PRIMARY KEY,
    title CHARACTER VARYING(50),
    year_publishing DATE,
    annotation CHARACTER VARYING(1000));

CREATE TABLE books_authors
    (books_id SMALLINT,
    authors_id SMALLINT,
    PRIMARY KEY(books_id, authors_id),
    FOREIGN KEY (books_id) REFERENCES books (id),
    FOREIGN KEY (authors_id) REFERENCES authors (id));

CREATE TABLE users
    (id SERIAL PRIMARY KEY,
    phone_number CHARACTER VARYING(50),
    password CHARACTER VARYING(200),
    role CHARACTER VARYING(20));

CREATE TABLE orders
    (id                   SERIAL PRIMARY KEY,
     date_created         TIMESTAMP,
     order_date_execution TIMESTAMP,
     status               CHARACTER VARYING(20)
);

CREATE TABLE order_item
(
    id       SERIAL PRIMARY KEY,
    order_id int,
    book_id  int,
    FOREIGN KEY (order_id) REFERENCES orders (id),
    FOREIGN KEY (book_id) REFERENCES books (id),
    count    SMALLINT
);

alter table orders
    add user_id int;

alter table orders
    add constraint orders_users_id_fk
        foreign key (user_id) references users;

