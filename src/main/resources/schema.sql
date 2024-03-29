DROP TABLE IF EXISTS news CASCADE;
DROP TABLE IF EXISTS types CASCADE;

CREATE TABLE IF NOT EXISTS news(
    id integer GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name varchar(250),
    short_description varchar(1000),
    description varchar(7000),
    type_id BIGINT
);

CREATE TABLE IF NOT EXISTS types(
    id integer GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name varchar(100) UNIQUE,
    color varchar(50) UNIQUE
);