-- category
insert into category(id, name, expense, icon, color, user_defined, active) values(1, 'salary', 0, 'icon', 'blue', 0, 1);
insert into category(id, name, expense, icon, color, user_defined, active) values(2, 'transport', 1, 'icon', 'blue', 0, 1);
insert into category(id, name, expense, icon, color, user_defined, active) values(3, 'food and drinks', 1, 'icon', 'blue', 0, 1);
insert into category(id, name, expense, icon, color, user_defined, active) values(4, 'rental fee', 1, 'icon', 'blue', 0, 1);
insert into category(id, name, expense, icon, color, user_defined, active) values(5, 'part time', 0, 'icon', 'blue', 0, 1);
insert into category(id, name, expense, icon, color, user_defined, active) values(6, 'games', 1, 'icon', 'blue', 0, 1);
insert into category(id, name, expense, icon, color, user_defined, active) values(7, 'online services', 1, 'icon', 'blue', 0, 1);

-- wallet
insert into wallet(id, name, balance_amount, default_currency_code, default_wallet) values(1, 'My Wallet 1', 1000, 'MYR', 1);
insert into wallet_categories(wallet_id, categories_id) values(1, 1);
insert into wallet_categories(wallet_id, categories_id) values(1, 2);
insert into wallet_categories(wallet_id, categories_id) values(1, 3);
insert into wallet_categories(wallet_id, categories_id) values(1, 4);

insert into wallet(id, name, balance_amount, default_currency_code, default_wallet) values(2, 'My Wallet 2', 2000, 'USD', 0);
insert into wallet_categories(wallet_id, categories_id) values(2, 1);
insert into wallet_categories(wallet_id, categories_id) values(2, 5);

insert into wallet(id, name, balance_amount, default_currency_code, default_wallet) values(3, 'My Wallet 3', 3000, 'IDR', 0);
insert into wallet_categories(wallet_id, categories_id) values(3, 3);

-- budget
insert into budget(id, name, current_amount, limit_amount, period, wallet_id) values(1, 'Budget 1', 500, 1000, 'MONTHLY', 1);
insert into budget_categories(budget_id, categories_id) values(1, 3);
insert into budget_categories(budget_id, categories_id) values(1, 2);

insert into budget(id, name, current_amount, limit_amount, period, wallet_id) values(2, 'Budget 2', 200, 500, 'MONTHLY', 1);
insert into budget_categories(budget_id, categories_id) values(2, 2);

-- transaction
-- findAllByWalletId()
insert into transaction(id, note, amount, date, wallet_id, category_id) values(3, 'mokata', 18.15, '2022-04-20', 3, 3);
insert into transaction(id, note, amount, date, wallet_id, category_id) values(2, 'grabfood', 15.50, '2022-04-21', 1, 3);
insert into transaction(id, note, amount, date, wallet_id, category_id) values(4, '', 3000, '2022-04-01', 1, 1);
insert into transaction(id, note, amount, date, wallet_id, category_id) values(1, 'grabfood', 20.50, '2022-04-22', 1, 3);
insert into transaction(id, note, amount, date, wallet_id, category_id) values(5, 'sunway grab fee', 10, '2022-03-29', 1, 2);

-- findAllByWalletIdAndDateRange()
-- findAllByBudgetId()
-- findAllByCategoryIds()
-- findAllByCategoryIdsAndDateRange()
-- findAllByAmountRangeAndDateRange()