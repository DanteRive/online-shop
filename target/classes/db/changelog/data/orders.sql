--liquibase formatted sql
--changeset admin:orders-1
create sequence orders_s;
create table orders (
    id bigint not null,
    general_price float8 not null,
    user_id bigint not null,
    primary key (id)
)