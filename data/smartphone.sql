create table smartphones(
	id serial primary key,
	brand varchar(100),
	model varchar(100),
	balance int
);
insert into smartphones(brand, model, balance) values('Samsung', 'S10', 5);
update smartphones set balance=2;
select * from smartphones;
delete from smartphones;
