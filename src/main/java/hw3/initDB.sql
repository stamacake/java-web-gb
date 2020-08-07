BEGIN;

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (id bigserial primary key, name varchar(200));
INSERT INTO users (name) VALUES ('u1'),('u2'),('u3'),('u4'),('u5'),('u6'),('u7'),('u8');

DROP TABLE IF EXISTS lot CASCADE;
CREATE TABLE lot (id bigserial primary key, name varchar(200), current_bet int default 0, users bigint references users (id) on delete cascade, version int default 0);
INSERT INTO lot (name) VALUES ('l1'),('l2'),('l3'),('l4');

COMMIT;