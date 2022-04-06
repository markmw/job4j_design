create table departments(
	id serial primary key,
	name varchar(100)
);

create table emploers(
	id serial primary key,
	name varchar(100),
	departments_id int references departments(id)
);

insert into departments(name) values('Marketing');
insert into departments(name) values('Operations');
insert into departments(name) values('Finance');
insert into departments(name) values('Sales');

insert into emploers(name, departments_id) values('Tom', 1);
insert into emploers(name, departments_id) values('John', 2);
insert into emploers(name, departments_id) values('Samanta', 2);
insert into emploers(name, departments_id) values('Anastasiya', 3);
insert into emploers(name) values('Alex');

select * 
from departments d
left join emploers e on d.id=e.departments_id;

select * 
from departments d
right join emploers e on d.id=e.departments_id;

select * 
from departments d
full join emploers e on d.id=e.departments_id;

select * from departments cross join emploers;

select d.name, COUNT(e.name) as number_of_emploers
from departments d
left join emploers e on d.id=e.departments_id
where e.departments_id is null
group by d.id;

select * 
from departments d
left join emploers e on d.id=e.departments_id
where e.departments_id is not null;

select * 
from departments d
right join emploers e on d.id=e.departments_id
where e.departments_id is not null;

create table teens(
	id serial primary key,
	name varchar(100),
	gender char(100)
);

insert into teens(name, gender) values('Sara', 'female');
insert into teens(name, gender) values('John', 'male');
insert into teens(name, gender) values('Sam', 'male');
insert into teens(name, gender) values('Nikol', 'female');
insert into teens(name, gender) values('Tom', 'male');
insert into teens(name, gender) values('Alex', 'male');
insert into teens(name, gender) values('Margarita', 'female');

select t1.name, t1.gender, t2.name, t2.gender
from teens t1 cross join teens t2
where t1.gender = 'female'
and t2.gender = 'male'; 
