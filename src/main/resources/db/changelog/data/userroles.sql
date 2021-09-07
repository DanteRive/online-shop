--liquibase formatted sql
--changeset admin:user-1
create sequence user_roles_s;
create table user_roles (
    id bigint not null,
    role_id bigint not null,
    user_id bigint not null,
    primary key (id)
)