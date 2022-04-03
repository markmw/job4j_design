create database fauna;

create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date)
values('Red lobster', 15000, date '1967-11-23');

insert into fauna(name, avg_age, discovery_date)
values('Small fish', 9000, date '1967-11-23');

insert into fauna(name, avg_age, discovery_date)
values('Bobcat', 25000, date '1877-08-03');

insert into fauna(name, avg_age, discovery_date)
values('Bulldog', 20000, date '1900-06-25');

insert into fauna(name, avg_age, discovery_date)
values('American eagle ', 10000, null);

select * from fauna;
select * from fauna where name like'%fish%';
select * from fauna where avg_age between 10000 and 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < to_date('1950','yyyy'); 
