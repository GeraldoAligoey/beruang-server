package com.gma.challenge.beruang.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
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

    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.SECOND, 5);

    Transaction rentalTransaction = new Transaction();
    rentalTransaction.setAmount(new BigDecimal(1000));
    rentalTransaction.setDate(calendar.getTime());
    rentalTransaction.setNote("Rental fee");

    Wallet wallet = new WalletDataSample().getSample();
    
    wallet.getCategories().forEach(category -> {
      if ("Rental".equals(category.getName())) {
        rentalTransaction.setCategory(category);
      }
    });

    rentalTransaction.setWallet(wallet);

    transactions.add(rentalTransaction);

    calendar.add(Calendar.SECOND, 5);

    Transaction foodDrinkTransaction = new Transaction();
    foodDrinkTransaction.setAmount(new BigDecimal(20));
    foodDrinkTransaction.setDate(calendar.getTime());
    foodDrinkTransaction.setNote("Food and drinks");

    wallet.getCategories().forEach(category -> {
      if ("Food and Drinks".equals(category.getName())) {
        foodDrinkTransaction.setCategory(category);
      }
    });

    foodDrinkTransaction.setWallet(wallet);

    transactions.add(foodDrinkTransaction);

    calendar.add(Calendar.SECOND, 5);
    Transaction salaryTransaction = new Transaction();
    salaryTransaction.setAmount(new BigDecimal(7500));
    salaryTransaction.setDate(calendar.getTime());
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
