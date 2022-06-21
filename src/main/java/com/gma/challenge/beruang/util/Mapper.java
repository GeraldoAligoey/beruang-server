package com.gma.challenge.beruang.util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.gma.challenge.beruang.data.BudgetData;
import com.gma.challenge.beruang.data.CategoryData;
import com.gma.challenge.beruang.data.NewBudgetRequestData;
import com.gma.challenge.beruang.data.NewCategoryRequestData;
import com.gma.challenge.beruang.data.NewTransactionRequestData;
import com.gma.challenge.beruang.data.NewWalletRequestData;
import com.gma.challenge.beruang.data.TransactionData;
import com.gma.challenge.beruang.data.UpdateBudgetRequestData;
import com.gma.challenge.beruang.data.UpdateCategoryRequestData;
import com.gma.challenge.beruang.data.UpdateTransactionRequestData;
import com.gma.challenge.beruang.data.UpdateWalletRequestData;
import com.gma.challenge.beruang.data.WalletData;
import com.gma.challenge.beruang.domain.Budget;
import com.gma.challenge.beruang.domain.Category;
import com.gma.challenge.beruang.domain.Transaction;
import com.gma.challenge.beruang.domain.Wallet;

public class Mapper {

  public static CategoryData toCategoryData(Category category) {
    CategoryData categoryData = new CategoryData();
    BeanUtils.copyProperties(category, categoryData);

    return categoryData;
  }

  public static Category toCategory(CategoryData categoryData) {
    Category category = new Category();
    BeanUtils.copyProperties(category, categoryData);

    return category;
  }

  public static Category toCategory(NewCategoryRequestData requestData) {
    Category category = new Category();
    BeanUtils.copyProperties(requestData, category);

    return category;
  }

  public static Category updateCategory(Category category, UpdateCategoryRequestData requestData) {
    if (requestData.getName() != null && !requestData.getName().isBlank()) {
      category.setName(requestData.getName());
    }

    if (requestData.getExpense() != null) {
      category.setExpense(requestData.getExpense());
    }

    if (requestData.getIcon() != null && !requestData.getIcon().isBlank()) {
      category.setIcon(requestData.getIcon());
    }

    if (requestData.getColor() != null && !requestData.getColor().isBlank()) {
      category.setColor(requestData.getColor());
    }

    return category;
  }

  public static WalletData toWalletData(Wallet wallet) {
    WalletData walletData = new WalletData();
    BeanUtils.copyProperties(wallet, walletData);

    List<CategoryData> categoryDatas = wallet.getCategories()
        .stream()
        .map(category -> toCategoryData(category))
        .collect(Collectors.toList());

    walletData.setCategories(categoryDatas);

    return walletData;
  }

  public static Wallet toWallet(NewWalletRequestData requestData) {
    Wallet wallet = new Wallet();
    wallet.setName(requestData.getName());
    wallet.setDefaultCurrencyCode(requestData.getDefaultCurrencyCode());

    Set<Category> categories = requestData.getCategoryIds()
        .stream()
        .map(categoryId -> toCategory(categoryId))
        .collect(Collectors.toSet());

    wallet.setCategories(categories);

    if (requestData.getInitialBalanceAmount() == null) {
      wallet.setInitialBalanceAmount(BigDecimal.ZERO);
    } else {
      wallet.setInitialBalanceAmount(requestData.getInitialBalanceAmount());
    }

    if (requestData.getDefaultWallet() != null) {
      wallet.setDefaultWallet(requestData.getDefaultWallet());
    }

    return wallet;
  }

  public static Category toCategory(Long categoryId) {
    Category category = new Category();
    category.setId(categoryId);

    return category;
  }

  public static Wallet updateWallet(Wallet wallet, UpdateWalletRequestData requestData) {
    if (requestData.getName() != null && !requestData.getName().isBlank()) {
      wallet.setName(requestData.getName());
    }

    if (requestData.getDefaultWallet() != null) {
      wallet.setDefaultWallet(requestData.getDefaultWallet());
    }

    if (requestData.getDefaultCurrencyCode() != null && !requestData.getDefaultCurrencyCode().isBlank()) {
      wallet.setDefaultCurrencyCode(requestData.getDefaultCurrencyCode());
    }

    if (requestData.getInitialBalanceAmount() != null) {
      wallet.setInitialBalanceAmount(requestData.getInitialBalanceAmount());
    }

    if (requestData.getCategoryIds() != null && !requestData.getCategoryIds().isEmpty()) {
      Set<Category> categories = requestData.getCategoryIds()
          .stream()
          .map(categoryData -> toCategory(categoryData))
          .collect(Collectors.toSet());
      wallet.setCategories(categories);
    }

    return wallet;
  }

  public static BudgetData toBudgetData(Budget budget) {
    BudgetData budgetData = new BudgetData();
    BeanUtils.copyProperties(budget, budgetData);
    budgetData.setWallet(toWalletData(budget.getWallet()));
    budgetData.setCategories(toCategoriesData(budget.getCategories()));

    return budgetData;
  }

  public static List<CategoryData> toCategoriesData(Collection<Category> categories) {
    if (categories != null) {
      return categories.stream()
          .map(category -> toCategoryData(category))
          .collect(Collectors.toList());
    }

    return null;
  }

  public static Budget toBudget(NewBudgetRequestData newBudgetRequestData) {
    Budget budget = new Budget();
    BeanUtils.copyProperties(newBudgetRequestData, budget);

    return budget;
  }

  public static Budget updateBudget(Budget budget, UpdateBudgetRequestData requestData) {
    if (requestData.getName() != null && !requestData.getName().isBlank()) {
      budget.setName(requestData.getName());
    }

    if (requestData.getPeriod() != null && !requestData.getPeriod().isBlank()) {
      budget.setPeriod(requestData.getPeriod());
    }

    if (requestData.getLimitAmount() != null) {
      budget.setLimitAmount(requestData.getLimitAmount());
    }

    if (requestData.getCategoryIds() != null && !requestData.getCategoryIds().isEmpty()) {
      Set<Category> categories = requestData.getCategoryIds()
          .stream()
          .map(categoryData -> toCategory(categoryData))
          .collect(Collectors.toSet());
      budget.setCategories(categories);
    }

    return budget;
  }

  public static TransactionData toTransactionData(Transaction transaction) {
    TransactionData transactionData = new TransactionData();
    BeanUtils.copyProperties(transaction, transactionData);    

    transactionData.category(toCategoryData(transaction.getCategory()));
    transactionData.wallet(toWalletData(transaction.getWallet()));

    return transactionData;
  }

  public static Transaction toTransaction(NewTransactionRequestData newTransactionRequestData) {
    Transaction transaction = new Transaction();
    BeanUtils.copyProperties(newTransactionRequestData, transaction); 
    
    return transaction;
  }

  public static Transaction updateTransaction(Transaction transaction,
      UpdateTransactionRequestData requestData) {
      
    if (requestData.getNote() != null) {
      transaction.setNote(requestData.getNote());
    }

    if (requestData.getAmount() != null) {
      transaction.setAmount(requestData.getAmount());
    }

    if (requestData.getDate() != null) {
      transaction.setDate(requestData.getDate());
    }

    return transaction;
  }

}
