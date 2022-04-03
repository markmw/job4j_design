create table contacts(
    id serial primary key,
    number int
);

create table users(
    id serial primary key,
    username varchar(255)
);

create table users_contacts(
    id serial primary key,
    contact_id int references contacts(id) unique,
    user_id int references users(id) unique
);
