create database if not exists  user;

create table if not exists user.auth
(
    id   int auto_increment
        primary key,
    name varchar(20) not null
);