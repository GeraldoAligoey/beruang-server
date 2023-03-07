package com.gma.challenge.beruang.service;

import com.gma.challenge.beruang.generated.dto.BudgetResponseData;
import com.gma.challenge.beruang.generated.dto.NewBudgetRequestData;
import com.gma.challenge.beruang.generated.dto.UpdateBudgetRequestData;

public interface BudgetWriteService {

    public BudgetResponseData createBudget(
            Long walletId, NewBudgetRequestData newBudgetRequestData);

    public BudgetResponseData updateBudget(
            Long walletId, Long budgetId, UpdateBudgetRequestData updateBudgetRequestData);

    public void deleteBudget(Long walletId, Long budgetId);
}
