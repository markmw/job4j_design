create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values('Xiaomi', 4999.99);
insert into devices(name, price) values('iPhone', 100000.99);
insert into devices(name, price) values('Pixel', 50100.89);
insert into people(name) values('Ramil');
insert into people(name) values('Volodya');
insert into people(name) values('Slava');
insert into devices_people(device_id, people_id) values(1, 1);
insert into devices_people(device_id, people_id) values(2, 2);
insert into devices_people(device_id, people_id) values(3, 2);
insert into devices_people(device_id, people_id) values(1, 3);
insert into devices_people(device_id, people_id) values(2, 3);
insert into devices_people(device_id, people_id) values(3, 3);

select avg(price)
from devices d;

select p.name, avg(d.price) from devices d
inner join devices_people dp on d.id=dp.device_id
inner join people p on p.id=dp.people_id
group by dp.people_id, p.name;

elect p.name, avg(d.price) from devices d
inner join devices_people dp on d.id=dp.device_id
inner join people p on p.id=dp.people_id
group by dp.people_id, p.name
having avg(d.price) > 5000;
