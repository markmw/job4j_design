create table body(
	id serial primary key,
	name varchar(255)
);
create table engine(
	id serial primary key,
	name varchar(255)
);
create table transmission(
	id serial primary key,
	name varchar(255)
);
create table car(
	id serial primary key,
	name varchar(255),
	body_id int references body(id),
	engine_id int references engine(id),
	transmission_id int references transmission(id)
);

insert into body(name) values('Седан');
insert into body(name) values('Универсал');
insert into body(name) values('Кабриолет');
insert into body(name) values('Лимузин');
insert into engine(name) values('ДВС');

insert into engine(name) values('двигатель Стирлинга');
insert into engine(name) values('двигатель Ванкеля');
insert into engine(name) values('Паровой двигатель');

insert into transmission(name) values('Механическая');
insert into transmission(name) values('Автоматическая');
insert into transmission(name) values('Полуавтоматическая');
insert into transmission(name) values('Бесступенчатая');

insert into car(name, body_id, engine_id, transmission_id)
values('Volkswagen', 1, 1, 2);
insert into car(name, body_id, engine_id, transmission_id)
values('Toyota', 2, 3, 3);
insert into car(name, body_id, engine_id, transmission_id)
values('Hyundai', 3, 2, 1);
insert into car(name, engine_id, transmission_id)
values('Chevrolet', 1, 1);
insert into car(name, body_id, transmission_id)
values('Tesla', 2, 3);
insert into car(name, body_id, engine_id)
values('Hyundai', 2, 1);

select c.name car, b.name body, e.name engine, t.name transmission
from car c
left join body b on c.body_id=b.id
left join engine e on c.engine_id=e.id
left join transmission t on c.body_id=t.id;

select b.name
from body b
left join car c on b.id=c.body_id
where c.id is null;

select e.name
from engine e
left join car c on e.id=c.engine_id
where c.id is null;

select t.name
from transmission t
left join car c on t.id=c.transmission_id
where c.id is null;
