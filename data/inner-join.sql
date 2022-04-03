create table country(
	id serial primary key,
	name varchar(255),
);

create table city(
	id serial primary key,
	name varchar(255),
	population int,
	country_id int references country(id)
);
insert into country(name) values('USA');
insert into country(name) values('Germany');
insert into country(name) values('Russia');

insert into city(name, population, country_id)
values('Texas', 200000000, 1);

insert into city(name, population, country_id)
values('Hamburg', 17000000, 2);

insert into city(name, population, country_id)
values('Moscow', 22000000, 3);

insert into city(name, population)
values('LA', 25000000);

select *
from city ct
inner join country cu on ct.country_id=cu.id;

select ct.name Name_of_City, ct.population
from city ct
inner join country cu on ct.country_id=cu.id;

select ct.name Name_of_City, cu.name Name_of_Country
from city ct
inner join country cu on ct.country_id=cu.id; 
