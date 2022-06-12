insert into category(name, expense, icon, color, user_defined, active) values('salary', 0, 'icon', 'blue', 0, 1);
insert into category(name, expense, icon, color, user_defined, active) values('transport', 1, 'icon', 'blue', 0, 1);
insert into category(name, expense, icon, color, user_defined, active) values('food and drinks', 1, 'icon', 'blue', 0, 1);
insert into category(name, expense, icon, color, user_defined, active) values('rental fee', 1, 'icon', 'blue', 0, 1);

insert into wallet(name, initial_balance_amount, default_currency_code, default_wallet) values('My Wallet 1', 1000, 'MYR', 1);
insert into wallet(name, initial_balance_amount, default_currency_code, default_wallet) values('My Wallet 2', 500, 'MYR', 0);

insert into wallet_categories(wallet_id, categories_id) values(
  (select w.id from wallet w where w.name = 'My Wallet 1'),
  (select c.id from category c where c.name = 'salary'));
insert into wallet_categories(wallet_id, categories_id) values(
  (select w.id from wallet w where w.name = 'My Wallet 1'),
  (select c.id from category c where c.name = 'transport'));
insert into wallet_categories(wallet_id, categories_id) values(
  (select w.id from wallet w where w.name = 'My Wallet 1'),
  (select c.id from category c where c.name = 'food and drinks'));
insert into wallet_categories(wallet_id, categories_id) values(
  (select w.id from wallet w where w.name = 'My Wallet 1'), 
  (select c.id from category c where c.name = 'rental fee'));
insert into wallet_categories(wallet_id, categories_id) values(
  (select w.id from wallet w where w.name = 'My Wallet 2'),
  (select c.id from category c where c.name = 'salary'));
insert into wallet_categories(wallet_id, categories_id) values(
  (select w.id from wallet w where w.name = 'My Wallet 2'),
  (select c.id from category c where c.name = 'transport'));
insert into wallet_categories(wallet_id, categories_id) values(
  (select w.id from wallet w where w.name = 'My Wallet 2'),
  (select c.id from category c where c.name = 'food and drinks'));
insert into wallet_categories(wallet_id, categories_id) values(
  (select w.id from wallet w where w.name = 'My Wallet 2'), 
  (select c.id from category c where c.name = 'rental fee'));