
insert into category(id, name, expense, icon, color, user_defined, active) values(500, 'salary', 0, 'icon', 'blue', 0, 1);
insert into category(id, name, expense, icon, color, user_defined, active) values(501, 'transport', 1, 'icon', 'blue', 0, 1);
insert into category(id, name, expense, icon, color, user_defined, active) values(502, 'food and drinks', 1, 'icon', 'blue', 0, 1);
insert into category(id, name, expense, icon, color, user_defined, active) values(503, 'rental fee', 1, 'icon', 'blue', 0, 1);

insert into wallet(id, name, initial_balance_amount, default_currency_code, default_wallet) values(500, 'Test Delete', 1000, 'MYR', 1);
insert into wallet(id, name, initial_balance_amount, default_currency_code, default_wallet) values(501, 'Test Delete', 1000, 'MYR', 1);

insert into wallet_categories(wallet_id, categories_id) values(
  500, 500);
insert into wallet_categories(wallet_id, categories_id) values(
  500, 501);