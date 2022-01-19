package com.gma.challenge.beruang.util;

import java.util.ArrayList;
import java.util.List;

import com.gma.challenge.beruang.model.Category;
import com.gma.challenge.beruang.repo.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class CategoryDataSample implements DataSampleService<Category> {

  @Autowired
  CategoryRepository transactionCategoryRepository;
  
  @Override
  public Category getSample() {
    Category expenseFoodDrink = new Category();
    expenseFoodDrink.setName("Food and Drinks");
    expenseFoodDrink.setExpense(true);
    expenseFoodDrink.setUserDefined(false);
    expenseFoodDrink.setIcon("food-and-drinks.png");

    return expenseFoodDrink;
  }

  @Override
  public List<Category> getSamples() {
    List<Category> categories = new ArrayList<>();

    Category expenseFoodDrink = new Category();
    expenseFoodDrink.setName("Food and Drinks");
    expenseFoodDrink.setExpense(true);
    expenseFoodDrink.setUserDefined(false);
    expenseFoodDrink.setIcon("food-and-drinks.png");

    categories.add(expenseFoodDrink);

    Category expenseRental = new Category();
    expenseRental.setName("Rental");
    expenseRental.setExpense(true);
    expenseRental.setUserDefined(false);
    expenseRental.setIcon("rental.png");

    categories.add(expenseRental);

    Category incomeSalary = new Category();
    incomeSalary.setName("Salary");
    incomeSalary.setExpense(false);
    incomeSalary.setUserDefined(false);
    incomeSalary.setIcon("salary.png");

    categories.add(incomeSalary);

    Category incomeBonus = new Category();
    incomeBonus.setName("Bonus");
    incomeBonus.setExpense(false);
    incomeBonus.setUserDefined(false);
    incomeBonus.setIcon("bonus.png");

    categories.add(incomeBonus);

    return categories;
  }
}
