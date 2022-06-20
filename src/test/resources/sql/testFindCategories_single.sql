delete from wallet_category;
delete from budget_category;
delete from transaction;
delete from budget;
delete from wallet;
delete from category;
insert into category(name, expense, icon, color, user_defined, active) values('salary', 0, 'icon', 'blue', 0, 1);