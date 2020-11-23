create schema if not exists hackday;

create extension if not exists "uuid-ossp";

create table hackday.client (
    id uuid default uuid_generate_v4 (),
    name text not null,
    address jsonb not null,
    primary key (id)
);
