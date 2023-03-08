package com.gma.challenge.beruang.budget.service;

import com.gma.challenge.beruang.generated.dto.BudgetResponseData;
import com.gma.challenge.beruang.generated.dto.BudgetsResponseData;

public interface BudgetReadService {

    public BudgetResponseData findBudget(Long walletId, Long budgetId);

    public BudgetsResponseData findBudgets(Long walletId);
}
