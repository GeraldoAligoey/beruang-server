package com.gma.challenge.beruang.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gma.challenge.beruang.data.NewWalletRequestData;
import com.gma.challenge.beruang.data.UpdateWalletRequestData;
import com.gma.challenge.beruang.data.WalletData;
import com.gma.challenge.beruang.data.WalletResponseData;
import com.gma.challenge.beruang.domain.Budget;
import com.gma.challenge.beruang.domain.Category;
import com.gma.challenge.beruang.domain.Wallet;
import com.gma.challenge.beruang.exception.CategoryNotFoundException;
import com.gma.challenge.beruang.exception.WalletNotFoundException;
import com.gma.challenge.beruang.repo.BudgetRepository;
import com.gma.challenge.beruang.repo.CategoryRepository;
import com.gma.challenge.beruang.repo.TransactionRepository;
import com.gma.challenge.beruang.repo.WalletRepository;
import com.gma.challenge.beruang.util.Mapper;
import com.gma.challenge.beruang.util.Validator;

@Transactional
@Service
public class WalletWriteServiceImpl implements WalletWriteService {

  private final WalletRepository walletRepository;
  private final TransactionRepository transactionRepository;
  private final BudgetRepository budgetRepository;
  private final CategoryRepository categoryRepository;

  public WalletWriteServiceImpl(WalletRepository walletRepository, TransactionRepository transactionRepository,
      BudgetRepository budgetRepository, CategoryRepository categoryRepository) {
    this.walletRepository = walletRepository;
    this.transactionRepository = transactionRepository;
    this.budgetRepository = budgetRepository;
    this.categoryRepository = categoryRepository;
  }

  @Override
  public WalletResponseData createWallet(NewWalletRequestData newWalletRequestData) {
    Validator.validateNewWalletRequestData(newWalletRequestData);
    Wallet wallet = Mapper.toWallet(newWalletRequestData);
    Set<Category> categories = new HashSet<Category>();

    for (Long categoryId : newWalletRequestData.getCategoryIds()) {
      categories.add(categoryRepository.getReferenceById(categoryId));
    }

    wallet.setCategories(categories);
    wallet = walletRepository.saveAndFlush(wallet);

    return WalletResponseData.builder().wallet(Mapper.toWalletData(wallet)).build();
  }

  @Override
  public WalletResponseData updateWallet(Long walletId, UpdateWalletRequestData updateWalletRequestData) {
    try {
      Validator.validateUpdateWalletRequestData(updateWalletRequestData);

      Wallet wallet = walletRepository.getReferenceById(walletId);

      Mapper.updateWallet(wallet, updateWalletRequestData);

      if (updateWalletRequestData.getCategoryIds() != null && !updateWalletRequestData.getCategoryIds().isEmpty()) {
        Set<Category> oldCategories = wallet.getCategories();
        Set<Category> newCategories = new HashSet<>();

        for (Long categoryId : updateWalletRequestData.getCategoryIds()) {
          Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("Invalid category id"));

          if (oldCategories.contains(category)) {
            oldCategories.remove(category);
          }

          newCategories.add(category);
        }

        wallet.setCategories(newCategories);

        List<Budget> budgets = budgetRepository.findByWalletId(wallet.getId());
        for (Category category : oldCategories) {
          if (!newCategories.contains(category)) {
            transactionRepository.deleteByWalletIdAndCategoryId(wallet.getId(), category.getId());
            for (Budget budget : budgets) {
              budget.removeCategory(category);
              budgetRepository.save(budget);
            }
          }
        }
      }

      WalletData walletData = Mapper.toWalletData(walletRepository.saveAndFlush(wallet));

      return WalletResponseData.builder().wallet(walletData).build();
    } catch (EntityNotFoundException ex) {
      throw new WalletNotFoundException("Invalid wallet id");
    }
  }

  @Override
  public void deleteWallet(Long walletId) {
    Wallet wallet = walletRepository.findById(walletId)
        .orElseThrow(() -> new WalletNotFoundException("Wallet id " + walletId + " not found"));

    walletRepository.delete(wallet);
  }

  @Override
  public WalletResponseData setDefaultTrue(Long walletId) {
    Wallet wallet = walletRepository.findById(walletId).orElseThrow(() -> new WalletNotFoundException("Invalid wallet id"));
    walletRepository.setAllDefaultFalse();
    wallet.setDefaultWallet(true);

    return WalletResponseData.builder().wallet(Mapper.toWalletData(walletRepository.saveAndFlush(wallet))).build();
  }

}