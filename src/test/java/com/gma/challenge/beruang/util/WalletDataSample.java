package com.gma.challenge.beruang.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.gma.challenge.beruang.model.TransactionCategory;
import com.gma.challenge.beruang.model.Wallet;

public class WalletDataSample implements DataSampleService<Wallet> {

  @Override
  public Wallet getSample() {
    Wallet wallet = new Wallet();

    wallet.setDefaultCurrencyCode("MYR");
    wallet.setDefaultWallet(true);
    wallet.setName("Test Wallet 001");
    wallet.setBalanceAmount(new BigDecimal(1000));
    wallet.setCategories(createTransactionCategories());

    return wallet;
  }

  @Override
  public List<Wallet> getSamples() {
    List<Wallet> wallets = new ArrayList<>();

    Wallet walletMyr = new Wallet();
    walletMyr.setDefaultCurrencyCode("MYR");
    walletMyr.setDefaultWallet(true);
    walletMyr.setName("Test Wallet 001");
    walletMyr.setBalanceAmount(new BigDecimal(1000));
    walletMyr.setCategories(createTransactionCategories());

    wallets.add(walletMyr);

    Wallet walletUsd = new Wallet();
    walletUsd.setDefaultCurrencyCode("USD");
    walletUsd.setDefaultWallet(false);
    walletUsd.setName("Test Wallet 002");
    walletUsd.setBalanceAmount(new BigDecimal(3000));
    walletUsd.setCategories(createTransactionCategories());
    
    wallets.add(walletUsd);

    return wallets;
  }

  private List<TransactionCategory> createTransactionCategories() {
    List<TransactionCategory> categories = new ArrayList<>();

    TransactionCategory expenseFoodDrink = new TransactionCategory();
    expenseFoodDrink.setName("Food and Drinks");
    expenseFoodDrink.setExpense(true);
    expenseFoodDrink.setUserDefined(false);
    expenseFoodDrink.setIcon("food-and-drinks.png");

    categories.add(expenseFoodDrink);

    TransactionCategory expenseRental = new TransactionCategory();
    expenseRental.setName("Rental");
    expenseRental.setExpense(true);
    expenseRental.setUserDefined(false);
    expenseRental.setIcon("rental.png");

    categories.add(expenseRental);

    TransactionCategory incomeSalary = new TransactionCategory();
    incomeSalary.setName("Salary");
    incomeSalary.setExpense(false);
    incomeSalary.setUserDefined(false);
    incomeSalary.setIcon("salary.png");

    categories.add(incomeSalary);

    TransactionCategory incomeBonus = new TransactionCategory();
    incomeBonus.setName("Bonus");
    incomeBonus.setExpense(false);
    incomeBonus.setUserDefined(false);
    incomeBonus.setIcon("bonus.png");

    categories.add(incomeBonus);

    return categories;
  }
}
