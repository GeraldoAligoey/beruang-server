package com.gma.challenge.beruang.service;

import com.gma.challenge.beruang.data.BudgetResponseData;
import com.gma.challenge.beruang.data.NewBudgetRequestData;
import com.gma.challenge.beruang.data.UpdateBudgetRequestData;

public interface BudgetWriteService {

  public BudgetResponseData createBudget(Long walletId, NewBudgetRequestData newBudgetRequestData);

  public BudgetResponseData updateBudget(Long walletId, Long budgetId, UpdateBudgetRequestData updateBudgetRequestData);

  public void deleteBudget(Long walletId, Long budgetId);
}
