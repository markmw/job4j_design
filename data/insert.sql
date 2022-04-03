insert into role(name) values('Admin');
insert into role(name) values('User1');
insert into category(name) values('Export');
insert into category(name) values('Home market');
insert into users(name, role_id) values('Petya', 1);
insert into users(name, role_id) values('Vasya', 2);
insert into rule(name) values('Can delete users');
insert into rule(name) values('Can add items');
insert into rule(name) values('Can update items');
insert into role_rules(role_id, rule_id) values(1,1);
insert into role_rules(role_id, rule_id) values(1,2);
insert into role_rules(role_id, rule_id) values(1,3);
insert into role_rules(role_id, rule_id) values(2,2);
insert into role_rules(role_id, rule_id) values(2,3);
insert into state(complete) values(true);
insert into state(complete) values(false);
insert into item(name, users_id, category_id, state_id) values('New platform', 1, 2, 1);
insert into item(name, users_id, category_id, state_id) values('Customer search', 2, 1, 2);
insert into comments(name, item_id) values('Someone comments', 1);
insert into comments(name, item_id) values('Someone comments search', 2);
insert into attachs(name, item_id) values('File1', 1);
insert into attachs(name, item_id) values('File2', 1);
insert into attachs(name, item_id) values('File3', 2);
insert into attachs(name, item_id) values('File4', 2);