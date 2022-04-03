 create table users(
     id serial primary key,
     name varchar(255)
 );
 
 create table products(
     id serial primary key,
     name varchar(255)
 );
 
 create table user_products(
     id serial primary key,
     users_id int references users(id),
     products_id int references products(id)
 );
