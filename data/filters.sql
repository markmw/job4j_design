create table type(
	id serial primary key, 
	name varchar(100)
);
create table product(
	id serial primary key, 
	name varchar(100), 
	type_id int references type(id), 
	expired_date date, 
	price float
);

insert into type(name) values('СЫР');
insert into type(name) values('МЯСО');
insert into type(name) values('РЫБА');
insert into type(name) values('МОЛОКО');

insert into product(name, type_id, expired_date, price)
values('Сыр косичка', 1, '2022-10-18', 249.6);
insert into product(name, type_id, expired_date, price)
values('Томский сыр', 1, '2021-12-30', 108.69);
insert into product(name, type_id, expired_date, price)
values('Сыр плавленный Дружба', 1, '2022-02-01', 23.99);
insert into product(name, type_id, expired_date, price)
values('Сыр полутвердый Ламбер', 1, '2022-02-15', 279.99);
insert into product(name, type_id, expired_date, price)
values('Сыр Хохланд', 1, '2022-05-20', 199.49);
insert into product(name, type_id, expired_date, price)
values('Сыр Тильзитер', 1, '2022-04-21', 101.49);
insert into product(name, type_id, expired_date, price)
values('Сыр Моццарелла', 1, '2022-01-20', 146.49);
insert into product(name, type_id, expired_date, price)
values('Сыр Маасдам', 1, '2022-05-20', 127.49);
insert into product(name, type_id, expired_date, price)
values('Сыр Голландский', 1, '2022-02-011', 360.49);
insert into product(name, type_id, expired_date, price)
values('Сыр голубой с плесенью', 1, '2022-03-01', 250.49);
insert into product(name, type_id, expired_date, price)
values('Сыр Гауда', 1, '2022-01-08', 158.49);
insert into product(name, type_id, expired_date, price)
values('Сыр импорт', 1, '2022-02-08', 769.99);
insert into product(name, type_id, expired_date, price)
values('Мороженое мясо',2, '2022-01-07', 599.99);
insert into product(name, type_id, expired_date, price)
values('Филе куриное', 2, '2022-01-13', 342.89);
insert into product(name, type_id, expired_date, price)
values('Говядина высший сорт', 2, '2022-01-15', 769.99);
insert into product(name, type_id, expired_date, price)
values('Сельдь тихооканская', 3, '2022-02-15', 166.99);
insert into product(name, type_id, expired_date, price)
values('Рыба красная', 3, '2022-01-09', 255.99);
insert into product(name, type_id, expired_date, price)
values('Кефир 5%', 4, '2022-01-16', 59.99);
insert into product(name, type_id, expired_date, price)
values('Томское молоко', 4, '2022-01-12', 79.99);
insert into product(name, type_id, expired_date, price)
values('Сметана 9%', 4, '2022-01-11', 66.99);

select *
from product p, type t
where p.type_id=t.id
and LOWER(t.name) like'сыр';

select *
from product p, type t
where p.type_id=t.id
and LOWER(p.name) like'%мороженое%';

select *
from product p, type t
where p.type_id=t.id
and p.expired_date < current_date;

select *
from product p
where p.price = (
select MAX(p1.price) from product p1
);

select t.name as Название_типа, COUNT(p.id)
from product p, type t
where p.type_id=t.id
group by t.name;

select *
from product p, type t
where p.type_id=t.id
and t.name in ('МОЛОКО', 'СЫР');

select t.name, COUNT(p.type_id)
from product p, type t
where p.type_id=t.id
group by t.name
having COUNT(p.type_id)<10;

select p.name Имя_продукта, t.name Тип_продукта
from product p, type t
where p.type_id=t.id;
