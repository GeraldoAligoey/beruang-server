insert into category(id, name, expense, icon, color, user_defined, active) values(1, 'salary', 0, 'icon', 'blue', 0, 1);
insert into category(id, name, expense, icon, color, user_defined, active) values(2, 'transport', 1, 'icon', 'blue', 0, 1);
insert into category(id, name, expense, icon, color, user_defined, active) values(3, 'food and drinks', 1, 'icon', 'blue', 0, 1);
insert into category(id, name, expense, icon, color, user_defined, active) values(4, 'rental fee', 1, 'icon', 'blue', 0, 1);
insert into category(id, name, expense, icon, color, user_defined, active) values(5, 'part time', 0, 'icon', 'blue', 0, 1);
insert into category(id, name, expense, icon, color, user_defined, active) values(6, 'games', 1, 'icon', 'blue', 0, 1);
insert into category(id, name, expense, icon, color, user_defined, active) values(7, 'online services', 1, 'icon', 'blue', 0, 1);

insert into wallet(id, name, initial_balance_amount, default_currency_code, default_wallet) values(1, 'My Wallet 1', 1000, 'MYR', 1);
insert into wallet(id, name, initial_balance_amount, default_currency_code, default_wallet) values(2, 'My Wallet 2', 2000, 'USD', 0);
insert into wallet(id, name, initial_balance_amount, default_currency_code, default_wallet) values(3, 'My Wallet 3', 3000, 'IDR', 0);

insert into wallet_categories(wallet_id, categories_id) values(1, 1);
insert into wallet_categories(wallet_id, categories_id) values(1, 2);
insert into wallet_categories(wallet_id, categories_id) values(1, 3);
insert into wallet_categories(wallet_id, categories_id) values(1, 4);

insert into wallet_categories(wallet_id, categories_id) values(2, 1);
insert into wallet_categories(wallet_id, categories_id) values(2, 5);

insert into wallet_categories(wallet_id, categories_id) values(3, 3);

insert into budget(id, name, current_amount, limit_amount, period, wallet_id) values(1, 'Budget 1', 500, 1000, 'MONTHLY', 1);

insert into budget_categories(budget_id, categories_id) values(1, 2);
insert into budget_categories(budget_id, categories_id) values(1, 3);