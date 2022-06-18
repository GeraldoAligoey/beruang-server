insert into category(name, expense, icon, color, user_defined, active) values('salary', 0, 'icon', 'blue', 0, 1);
insert into category(name, expense, icon, color, user_defined, active) values('transport', 1, 'icon', 'blue', 0, 1);
insert into category(name, expense, icon, color, user_defined, active) values('food and drinks', 1, 'icon', 'blue', 0, 1);
insert into category(name, expense, icon, color, user_defined, active) values('rental fee', 1, 'icon', 'blue', 0, 1);
insert into category(name, expense, icon, color, user_defined, active) values('part time', 0, 'icon', 'blue', 0, 1);
insert into category(name, expense, icon, color, user_defined, active) values('games', 1, 'icon', 'blue', 0, 1);
insert into category(name, expense, icon, color, user_defined, active) values('online services', 1, 'icon', 'blue', 0, 1);

insert into wallet(name, initial_balance_amount, default_currency_code, default_wallet) values('My Wallet 1', 1000, 'MYR', 1);
insert into wallet(name, initial_balance_amount, default_currency_code, default_wallet) values('My Wallet 2', 2000, 'USD', 0);
insert into wallet(name, initial_balance_amount, default_currency_code, default_wallet) values('My Wallet 3', 3000, 'IDR', 0);

insert into wallet_category(wallet_id, category_id) values(1, 1);
insert into wallet_category(wallet_id, category_id) values(1, 2);
insert into wallet_category(wallet_id, category_id) values(1, 3);
insert into wallet_category(wallet_id, category_id) values(1, 4);

insert into wallet_category(wallet_id, category_id) values(2, 1);
insert into wallet_category(wallet_id, category_id) values(2, 5);

insert into wallet_category(wallet_id, category_id) values(3, 3);

insert into budget(name, current_amount, limit_amount, period, wallet_id) values('Budget 1', 1000, 5000, 'MONTHLY', 1);
insert into budget(name, current_amount, limit_amount, period, wallet_id) values('Budget 2', 1000, 5000, 'MONTHLY', 2);
insert into budget(name, current_amount, limit_amount, period, wallet_id) values('Budget 3', 1000, 5000, 'MONTHLY', 1);

insert into budget_category(budget_id, category_id) values(1, 1);
insert into budget_category(budget_id, category_id) values(1, 2);
insert into budget_category(budget_id, category_id) values(1, 3);

insert into budget_category(budget_id, category_id) values(2, 3);
insert into budget_category(budget_id, category_id) values(2, 4);

insert into budget_category(budget_id, category_id) values(3, 3);
insert into budget_category(budget_id, category_id) values(3, 4);

insert into transaction(amount, date, note, category_id, wallet_id) values(100, CURRENT_DATE, '', 1, 1);
insert into transaction(amount, date, note, category_id, wallet_id) values(5, CURRENT_DATE, '', 2, 1);
insert into transaction(amount, date, note, category_id, wallet_id) values(12.5, CURRENT_DATE, '', 4, 2);
insert into transaction(amount, date, note, category_id, wallet_id) values(18.8, CURRENT_DATE, '', 4, 2);
insert into transaction(amount, date, note, category_id, wallet_id) values(50, CURRENT_DATE, '', 2, 1);