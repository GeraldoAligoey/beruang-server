package com.gma.challenge.beruang.service;

import com.gma.challenge.beruang.exception.BudgetNotFoundException;
import com.gma.challenge.beruang.exception.WalletNotFoundException;
import com.gma.challenge.beruang.generated.dto.BudgetResponseData;
import com.gma.challenge.beruang.generated.dto.BudgetsResponseData;
import com.gma.challenge.beruang.model.Budget;
import com.gma.challenge.beruang.repo.BudgetRepository;
import com.gma.challenge.beruang.service.util.Mapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class BudgetReadServiceImpl implements BudgetReadService {

    private final BudgetRepository budgetRepository;

    public BudgetReadServiceImpl(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @Override
    public BudgetResponseData findBudget(Long walletId, Long budgetId) {
        Budget budget =
                budgetRepository
                        .findById(budgetId)
                        .orElseThrow(
                                () ->
                                        new BudgetNotFoundException(
                                                "Budget id " + budgetId + " not found"));
        if (!budget.getWallet().getId().equals(walletId)) {
            throw new BudgetNotFoundException("The budget is not in the given wallet");
        }

        return BudgetResponseData.builder().budget(Mapper.toBudgetData(budget)).build();
    }

    @Override
    public BudgetsResponseData findBudgets(Long walletId) {
        List<Budget> budgets = budgetRepository.findByWalletId(walletId);

        if (!isWalletIdValid(walletId) || budgets == null) {
            throw new WalletNotFoundException("Invalid wallet id");
        }

        return BudgetsResponseData.builder()
                .budgets(
                        budgets.stream()
                                .map(budget -> Mapper.toBudgetData(budget))
                                .collect(Collectors.toList()))
                .build();
    }

    private boolean isWalletIdValid(Long walletId) {
        return walletId > 0;
    }
}
