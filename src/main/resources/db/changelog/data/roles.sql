--liquibase formatted sql
--changeset admin:user-1
create sequence roles_s;
create table roles (
    id bigint not null,
    name varchar(255),
    primary key (id)
)