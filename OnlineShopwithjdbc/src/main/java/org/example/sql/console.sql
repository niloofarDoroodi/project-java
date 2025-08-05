create database shop;

create table shareholder
(
    shareholder_id   bigserial primary key,
    shareholder_name varchar(50)   not null,
    phone_number     bigint        not null,
    national_code    bigint unique not null
);

create table brand
(
    brand_id    bigserial primary key,
    brand_name  varchar(50) unique not null,
    website     varchar            not null,
    description varchar            not null
);

create table shareholder_brand
(
    brand_id       bigint,
    foreign key (brand_id) references brand (brand_id),
    shareholder_id bigint,
    foreign key (shareholder_id) references shareholder (shareholder_id)
);

create table category
(
    category_id   bigserial primary key,
    category_name varchar(50) unique not null,
    description   varchar            not null
);

create table product
(
    product_id   bigserial primary key not null ,
    product_name varchar(50) not null,
    created_date date,
    category_id  bigint,
    foreign key (category_id) references category (category_id),
    brand_id     bigint,
    foreign key (brand_id) references brand (brand_id)
);

create table users
(
    user_id   bigserial primary key,
    name      varchar(50)        not null,
    user_name varchar(50) unique not null,
    password  varchar(50)        not null,
    email     varchar unique     not null
);