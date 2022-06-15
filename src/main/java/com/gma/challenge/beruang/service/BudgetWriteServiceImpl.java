package com.gma.challenge.beruang.service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gma.challenge.beruang.data.BudgetData;
import com.gma.challenge.beruang.data.BudgetResponseData;
import com.gma.challenge.beruang.data.NewBudgetRequestData;
import com.gma.challenge.beruang.data.UpdateBudgetRequestData;
import com.gma.challenge.beruang.domain.Budget;
import com.gma.challenge.beruang.domain.Wallet;
import com.gma.challenge.beruang.exception.BudgetNotFoundException;
import com.gma.challenge.beruang.exception.CategoryNotFoundException;
import com.gma.challenge.beruang.exception.WalletNotFoundException;
import com.gma.challenge.beruang.repo.BudgetRepository;
import com.gma.challenge.beruang.repo.CategoryRepository;
import com.gma.challenge.beruang.repo.WalletRepository;
import com.gma.challenge.beruang.util.Mapper;
import com.gma.challenge.beruang.util.Validator;

@Transactional
@Service
public class BudgetWriteServiceImpl implements BudgetWriteService {

  private final BudgetRepository budgetRepository;
  private final WalletRepository walletRepository;
  private final CategoryRepository categoryRepository;

  @Autowired
  public BudgetWriteServiceImpl(BudgetRepository budgetRepository, WalletRepository walletRepository,
      CategoryRepository categoryRepository) {
    this.budgetRepository = budgetRepository;
    this.walletRepository = walletRepository;
    this.categoryRepository = categoryRepository;
  }

  @Override
  public BudgetResponseData createBudget(Long walletId, NewBudgetRequestData newBudgetRequestData) {
    Validator.validateNewBudgetRequestData(newBudgetRequestData);
    Budget budget = Mapper.toBudget(newBudgetRequestData);

    try {
      for (Long categoryId : newBudgetRequestData.getCategoryIds()) {
        budget.addCategory(categoryRepository.getReferenceById(categoryId));
      }
    } catch (EntityNotFoundException ex) {
      throw new CategoryNotFoundException("Invalid category id");
    }

    try {
      Wallet wallet = walletRepository.getReferenceById(walletId);
      budget.setWallet(wallet);
    } catch (EntityNotFoundException ex) {
      throw new WalletNotFoundException("Invalid wallet id");
    }

    budget = budgetRepository.saveAndFlush(budget);
    return new BudgetResponseData().budget(Mapper.toBudgetData(budget));
  }

  @Override
  public BudgetResponseData updateBudget(Long walletId, Long budgetId,
      UpdateBudgetRequestData updateBudgetRequestData) {
    Validator.validateUpdateBudgetRequestData(updateBudgetRequestData);
    Budget budget = budgetRepository.findById(budgetId)
        .orElseThrow(() -> new BudgetNotFoundException("Invalid budget id"));

    if (budget.getWallet().getId() == walletId) {
      BudgetData budgetData = Mapper
          .toBudgetData(budgetRepository
              .saveAndFlush(Mapper
                  .updateBudget(budget, updateBudgetRequestData)));
      return new BudgetResponseData().budget(budgetData);
    } else {
      throw new WalletNotFoundException("Invalid wallet id");
    }
  }

  @Override
  public void deleteBudget(Long walletId, Long budgetId) {
    Budget budget = budgetRepository.findById(budgetId)
        .orElseThrow(() -> new BudgetNotFoundException("Invalid budget id"));

    budgetRepository.delete(budget);
  }

}
