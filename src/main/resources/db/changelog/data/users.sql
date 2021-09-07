--liquibase formatted sql
--changeset admin:user-1
create sequence users_s;
create table users (
    id bigint not null,
    active boolean not null,
    email varchar(255),
    password varchar(255),
    name varchar(255) not null,
    primary key (id)
)