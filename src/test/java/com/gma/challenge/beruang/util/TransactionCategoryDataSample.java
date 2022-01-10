package com.gma.challenge.beruang.util;

import java.util.ArrayList;
import java.util.List;

import com.gma.challenge.beruang.model.TransactionCategory;
import com.gma.challenge.beruang.repo.TransactionCategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class TransactionCategoryDataSample implements DataSampleService<TransactionCategory> {

  @Autowired
  TransactionCategoryRepository transactionCategoryRepository;
  
  @Override
  public TransactionCategory getSample() {
    TransactionCategory expenseFoodDrink = new TransactionCategory();
    expenseFoodDrink.setName("Food and Drinks");
    expenseFoodDrink.setExpense(true);
    expenseFoodDrink.setUserDefined(false);
    expenseFoodDrink.setIcon("food-and-drinks.png");

    return expenseFoodDrink;
  }

  @Override
  public List<TransactionCategory> getSamples() {
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
