package com.gma.challenge.beruang.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.gma.challenge.beruang.model.Transaction;
import com.gma.challenge.beruang.model.Wallet;

public class TransactionDataSample implements DataSampleService<Transaction> {

  @Override
  public Transaction getSample() {
    return getSamples().get(0);
  }

  @Override
  public List<Transaction> getSamples() {
    List<Transaction> transactions = new ArrayList<>();

    Transaction rentalTransaction = new Transaction();
    rentalTransaction.setAmount(new BigDecimal(1000));
    rentalTransaction.setDate(LocalDate.of(2022, 1, 1));
    rentalTransaction.setNote("Rental fee");

    Wallet wallet = new WalletDataSample().getSample();

    wallet.getCategories().forEach(category -> {
      if ("Rental".equals(category.getName())) {
        rentalTransaction.setCategory(category);
      }
    });

    rentalTransaction.setWallet(wallet);

    transactions.add(rentalTransaction);

    Transaction foodDrinkTransaction = new Transaction();
    foodDrinkTransaction.setAmount(new BigDecimal(20));
    foodDrinkTransaction.setDate(LocalDate.of(2022, 1, 2));
    foodDrinkTransaction.setNote("Food and drinks");

    wallet.getCategories().forEach(category -> {
      if ("Food and Drinks".equals(category.getName())) {
        foodDrinkTransaction.setCategory(category);
      }
    });

    foodDrinkTransaction.setWallet(wallet);

    transactions.add(foodDrinkTransaction);

    Transaction salaryTransaction = new Transaction();
    salaryTransaction.setAmount(new BigDecimal(7500));
    salaryTransaction.setDate(LocalDate.of(2022, 1, 3));
    salaryTransaction.setNote("Salary");

    wallet.getCategories().forEach(category -> {
      if ("Salary".equals(category.getName())) {
        salaryTransaction.setCategory(category);
      }
    });

    salaryTransaction.setWallet(wallet);

    transactions.add(salaryTransaction);

    return transactions;
  }

}
