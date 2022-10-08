DROP TABLE IF EXISTS users CASCADE;
DROP SEQUENCE IF EXISTS global_seq CASCADE;

CREATE SEQUENCE global_seq START WITH 100;

CREATE TABLE IF NOT EXISTS users
(
    id              BIGINT PRIMARY KEY DEFAULT nextval('global_seq'),
    first_name      VARCHAR(32)         NOT NULL,
    last_name       VARCHAR(32)         NOT NULL,
    password        VARCHAR(128)        NOT NULL,
    phone           VARCHAR(16) UNIQUE  NOT NULL
);

CREATE TABLE IF NOT EXISTS signin_users (
    id              BIGINT PRIMARY KEY DEFAULT nextval('global_seq'),
    user_id         BIGINT,
    date            TIMESTAMP(3),
    ip              VARCHAR
);

CREATE TABLE IF NOT EXISTS images (
    id              uuid PRIMARY KEY,
    user_id         BIGINT,
    name            VARCHAR,
    size            BIGINT,
    mime            VARCHAR
);