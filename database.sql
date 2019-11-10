CREATE EXTENSION IF NOT EXISTS dblink;
DO
$do$
    BEGIN
        IF EXISTS(SELECT FROM pg_database WHERE datname = 'lo54') THEN
            RAISE NOTICE 'Database already exists'; -- optional
        ELSE
            PERFORM dblink_exec('dbname=' || current_database() -- current db
                        , 'CREATE DATABASE lo54');
        END IF;
    END
$do$;

CREATE TABLE IF NOT EXISTS location
(
    id   SERIAL PRIMARY KEY NOT NULL,
    city text               NOT NULL
);

CREATE TABLE IF NOT EXISTS course
(
    code  text PRIMARY KEY NOT NULL,
    title text             not null
);

CREATE TABLE IF NOT EXISTS course_session
(
    id          SERIAL PRIMARY KEY NOT NULL,
    start_date  timestamp          not null,
    end_date    timestamp          not null,
    max         int,
    course_code text               NOT NULL REFERENCES course (code),
    location_id int                not null references location (id)
);

CREATE table if not exists client
(
    id                serial not null primary key,
    lastname          text   not null,
    firstname         text   not null,
    address           text   not null,
    phone             text   not null,
    email             text,
    course_session_id int    not null references course_session (id)
);