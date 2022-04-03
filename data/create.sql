create table role(
	id serial primary key,
	name char(100)
);

create table category(
	id serial primary key,
	name text
);

create table users(
	id serial primary key,
	name char(100),
	role_id int references role(id)
);

create table rule(
	id serial primary key,
	name text
);

create table role_rules(
	id serial primary key,
	role_id int references role(id),
	rule_id int references rule(id)
);

create table state(
	id serial primary key,
	complete boolean
);

create table item(
	id serial primary key,
	name text,
	users_id int references users(id),
	category_id int references category(id),
	state_id int references state(id)
);

create table comments(
	id serial primary key,
	name text,
	item_id int references item(id)
);

create table attachs(
	id serial primary key,
	name char(100),
	item_id int references item(id)
); 
