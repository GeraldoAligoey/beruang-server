package com.gma.challenge.beruang.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gma.challenge.beruang.data.BudgetResponseData;
import com.gma.challenge.beruang.data.BudgetsResponseData;
import com.gma.challenge.beruang.domain.Budget;
import com.gma.challenge.beruang.exception.BudgetNotFoundException;
import com.gma.challenge.beruang.exception.WalletNotFoundException;
import com.gma.challenge.beruang.repo.BudgetRepository;
import com.gma.challenge.beruang.util.Mapper;

@Service
public class BudgetReadServiceImpl implements BudgetReadService {

  private final BudgetRepository budgetRepository;

  @Autowired
  public BudgetReadServiceImpl(BudgetRepository budgetRepository) {
    this.budgetRepository = budgetRepository;
  }

  @Override
  public BudgetResponseData findBudget(Long walletId, Long budgetId) {
    Budget budget = budgetRepository.findById(budgetId)
        .orElseThrow(() -> new BudgetNotFoundException("Budget id " + budgetId + " not found"));
    if (!budget.getWallet().getId().equals(walletId)) {
      throw new BudgetNotFoundException("The budget is not in the given wallet");
    }

    return new BudgetResponseData().budget(Mapper.toBudgetData(budget));
  }

  @Override
  public BudgetsResponseData findBudgets(Long walletId) {
    List<Budget> budgets = budgetRepository.findAllByWalletId(walletId);

    if (!isWalletIdValid(walletId) || budgets == null) {
      throw new WalletNotFoundException("Invalid wallet id");
    }

    return new BudgetsResponseData()
        .budgets(budgets
            .stream()
            .map(budget -> Mapper.toBudgetData(budget))
            .collect(Collectors.toList()));
  }

  private boolean isWalletIdValid(Long walletId) {
    return walletId > 0;
  }

}