package com.gma.challenge.beruang.service;

import java.math.BigDecimal;
import java.util.List;

import com.gma.challenge.beruang.model.Budget;
import com.gma.challenge.beruang.model.Category;
import com.gma.challenge.beruang.model.Wallet;

public interface WriteBudgetService {

  public Budget createBudget(List<Category> categories, Wallet wallet, String budgetPeriod, BigDecimal limitAmount);

  public Budget updateBudgetById(Long id, Budget budget);

  public void deleteBudgetById(Long budgetId);
}
