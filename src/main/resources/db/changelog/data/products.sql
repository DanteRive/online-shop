--liquibase formatted sql
--changeset admin:products-1
create sequence products_s;
create table products (
    id bigint not null,
    name varchar(255) not null,
    price float8 not null,
    type varchar(30) not null,
    primary key (id)
)