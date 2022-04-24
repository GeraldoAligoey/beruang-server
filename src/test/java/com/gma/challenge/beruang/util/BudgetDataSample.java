package com.gma.challenge.beruang.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.gma.challenge.beruang.model.Budget;
import com.gma.challenge.beruang.model.Category;
import com.gma.challenge.beruang.model.Period;
import com.gma.challenge.beruang.model.Wallet;

public class BudgetDataSample implements DataSampleService<Budget> {

  @Override
  public Budget getSample() {

    return getSamples().get(0);
  }

  @Override
  public List<Budget> getSamples() {
    List<Budget> budgets = new ArrayList<>();

    Budget monthlyFoodBudget = new Budget();
    monthlyFoodBudget.setName("Monthly Food Budget");
    monthlyFoodBudget.setLimitAmount(new BigDecimal(1000));
    monthlyFoodBudget.setCurrentAmount(new BigDecimal(0));
    monthlyFoodBudget.setPeriod(Period.MONTHLY.getValue());
    monthlyFoodBudget.addCategory(new CategoryDataSample().getSample());
    monthlyFoodBudget.setWallet(new WalletDataSample().getSample());

    budgets.add(monthlyFoodBudget);

    Budget monthlyBudget = new Budget();
    monthlyBudget.setName("Monthly Spending Budget");
    monthlyBudget.setLimitAmount(new BigDecimal(3000));
    monthlyBudget.setCurrentAmount(new BigDecimal(0));
    monthlyBudget.setPeriod(Period.MONTHLY.getValue());
    monthlyBudget.setCategories(new HashSet<Category>(new CategoryDataSample().getSamples()));

    Wallet wallet = new WalletDataSample().getSample();
    wallet.setName("Spending wallet");

    monthlyBudget.setWallet(wallet);

    budgets.add(monthlyBudget);

    return budgets;
  }
  
}
