BEGIN;

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (id bigserial primary key, name varchar(200), age int);
INSERT INTO users (name,age) VALUES ('u1',20),('u2',21),('u3',22);

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial primary key, name varchar(200), price int);
INSERT INTO products (name, price) VALUES ('pr1',100),('pr2',200),('pr3',300);

COMMIT;