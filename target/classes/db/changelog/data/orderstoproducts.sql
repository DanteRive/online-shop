--liquibase formatted sql
--changeset admin:orderstoproducts-1
create sequence order_products_s;
create table orders_to_products (
    id bigint not null,
    order_id bigint not null,
    product_id bigint not null,
    primary key (id)
)