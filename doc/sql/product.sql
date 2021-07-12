create database if not exists  product;

create table if not exists product.orders
(
    id          int auto_increment
        primary key,
    description varchar(20) not null
);