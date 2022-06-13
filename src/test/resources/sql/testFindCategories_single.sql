delete from wallet_categories;
delete from budget_categories;
delete from budget;
delete from wallet;
delete from category;
insert into category(name, expense, icon, color, user_defined, active) values('salary', 0, 'icon', 'blue', 0, 1);