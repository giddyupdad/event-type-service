-- liquibase formatted sql

--changeset event_type:1

CREATE TABLE event_type
(
  id bigserial NOT NULL,
  type_code varchar(50),
  type_description text,
  create_by text,
  create_time timestamp with time zone,
  PRIMARY KEY (id)
);

CREATE INDEX event_type_code_index ON event_type(type_code ASC);
