package com.gma.challenge.beruang.service;

import com.gma.challenge.beruang.data.BudgetResponseData;
import com.gma.challenge.beruang.data.BudgetsResponseData;

public interface BudgetReadService {

  public BudgetResponseData findBudget(Long walletId, Long budgetId);

  public BudgetsResponseData findBudgets(Long walletId);
}
