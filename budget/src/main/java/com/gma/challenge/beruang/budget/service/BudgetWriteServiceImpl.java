package com.gma.challenge.beruang.budget.service;

import com.gma.challenge.beruang.budget.model.Budget;
import com.gma.challenge.beruang.budget.repo.BudgetRepository;
import com.gma.challenge.beruang.budget.util.Mapper;
import com.gma.challenge.beruang.budget.util.Validator;
import com.gma.challenge.beruang.category.model.Category;
import com.gma.challenge.beruang.category.repo.CategoryRepository;
import com.gma.challenge.beruang.common.exception.BudgetNotFoundException;
import com.gma.challenge.beruang.common.exception.CategoryNotFoundException;
import com.gma.challenge.beruang.common.exception.CategoryNotInWalletException;
import com.gma.challenge.beruang.common.exception.WalletNotFoundException;
import com.gma.challenge.beruang.generated.dto.BudgetData;
import com.gma.challenge.beruang.generated.dto.BudgetResponseData;
import com.gma.challenge.beruang.generated.dto.NewBudgetRequestData;
import com.gma.challenge.beruang.generated.dto.UpdateBudgetRequestData;
import com.gma.challenge.beruang.wallet.model.Wallet;
import com.gma.challenge.beruang.wallet.repo.WalletRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Transactional
@Service
public class BudgetWriteServiceImpl implements BudgetWriteService {

    private final BudgetRepository budgetRepository;
    private final WalletRepository walletRepository;
    private final CategoryRepository categoryRepository;

    public BudgetWriteServiceImpl(
            BudgetRepository budgetRepository,
            WalletRepository walletRepository,
            CategoryRepository categoryRepository) {
        this.budgetRepository = budgetRepository;
        this.walletRepository = walletRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public BudgetResponseData createBudget(
            Long walletId, NewBudgetRequestData newBudgetRequestData) {
        Validator.validateNewBudgetRequestData(newBudgetRequestData);
        Budget budget = Mapper.toBudget(newBudgetRequestData);

        Wallet wallet =
                walletRepository
                        .findById(walletId)
                        .orElseThrow(() -> new WalletNotFoundException("Invalid wallet id"));
        budget.setWallet(wallet);

        for (Long categoryId : newBudgetRequestData.getCategoryIds()) {
            Category category =
                    categoryRepository
                            .findById(categoryId)
                            .orElseThrow(
                                    () -> new CategoryNotFoundException("Invalid category id"));

            if (wallet.getCategories().contains(category)) {
                budget.addCategory(category);
            }
        }

        if (budget.getCategories().isEmpty()) {
            throw new CategoryNotInWalletException(
                    "The given category id is not part of the categories in the selected wallet");
        }

        budget = budgetRepository.saveAndFlush(budget);

        return BudgetResponseData.builder().budget(Mapper.toBudgetData(budget)).build();
    }

    @Override
    public BudgetResponseData updateBudget(
            Long walletId, Long budgetId, UpdateBudgetRequestData updateBudgetRequestData) {
        Validator.validateUpdateBudgetRequestData(updateBudgetRequestData);
        Budget budget =
                budgetRepository
                        .findById(budgetId)
                        .orElseThrow(() -> new BudgetNotFoundException("Invalid budget id"));

        if (budget.getWallet().getId() == walletId) {
            budget = Mapper.updateBudget(budget, updateBudgetRequestData);

            if (updateBudgetRequestData.getCategoryIds() != null
                    && !updateBudgetRequestData.getCategoryIds().isEmpty()) {
                Set<Category> budgetCategories = budget.getCategories();
                budgetCategories.clear();

                for (Long categoryIds : updateBudgetRequestData.getCategoryIds()) {
                    Category category = categoryRepository.getReferenceById(categoryIds);

                    if (budget.getWallet().getCategories().contains(category)) {
                        budgetCategories.add(category);
                    }
                }
            }

            if (budget.getCategories().isEmpty()) {
                throw new CategoryNotInWalletException(
                        "The given category id is not part of the categories in the selected"
                                + " wallet");
            }

            BudgetData budgetData = Mapper.toBudgetData(budgetRepository.saveAndFlush(budget));

            return BudgetResponseData.builder().budget(budgetData).build();
        } else {
            throw new WalletNotFoundException("Invalid wallet id");
        }
    }

    @Override
    public void deleteBudget(Long walletId, Long budgetId) {
        Budget budget =
                budgetRepository
                        .findById(budgetId)
                        .orElseThrow(() -> new BudgetNotFoundException("Invalid budget id"));

        if (!budget.getWallet().getId().equals(walletId)) {
            throw new WalletNotFoundException("Invalid wallet id");
        }

        budgetRepository.delete(budget);
    }
}
