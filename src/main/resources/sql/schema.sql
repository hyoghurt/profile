CREATE SEQUENCE IF NOT EXISTS global_seq START WITH 100;

CREATE TABLE IF NOT EXISTS users
(
    id              BIGINT PRIMARY KEY DEFAULT nextval('global_seq'),
    first_name      VARCHAR(32)         NOT NULL,
    last_name       VARCHAR(32)         NOT NULL,
    password        VARCHAR(128)        NOT NULL,
    phone           VARCHAR(16)         NOT NULL,
    email           VARCHAR(128) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS signin_users (
    id              BIGINT PRIMARY KEY DEFAULT nextval('global_seq'),
    user_id         BIGINT,
    date            TIMESTAMP(3),
    ip              VARCHAR
);

CREATE TABLE IF NOT EXISTS images (
    id              VARCHAR(64) PRIMARY KEY,
    user_id         BIGINT,
    name            VARCHAR,
    size            BIGINT,
    mime            VARCHAR
);