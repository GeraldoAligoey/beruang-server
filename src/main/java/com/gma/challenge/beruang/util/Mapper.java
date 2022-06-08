package com.gma.challenge.beruang.util;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.gma.challenge.beruang.data.CategoryData;
import com.gma.challenge.beruang.data.NewCategoryRequestData;
import com.gma.challenge.beruang.data.NewWalletRequestData;
import com.gma.challenge.beruang.data.UpdateCategoryRequestData;
import com.gma.challenge.beruang.data.UpdateWalletRequestData;
import com.gma.challenge.beruang.data.WalletData;
import com.gma.challenge.beruang.domain.Category;
import com.gma.challenge.beruang.domain.Wallet;

public class Mapper {

  public static CategoryData toCategoryData(Category category) {
    CategoryData categoryData = new CategoryData();
    categoryData.setId(category.getId());
    categoryData.setName(category.getName());
    categoryData.setExpense(category.isExpense());
    categoryData.setIcon(category.getIcon());
    categoryData.setColor(category.getColor());
    categoryData.setActive(category.isActive());

    return categoryData;
  }

  private static Category toCategory(CategoryData categoryData) {
    Category category = new Category();
    category.setId(categoryData.getId());
    category.setName(categoryData.getName());
    category.setExpense(categoryData.getExpense());
    category.setIcon(categoryData.getIcon());
    category.setColor(categoryData.getColor());
    category.setActive(true);

    return category;
  }

  public static Category toCategory(NewCategoryRequestData requestData) {
    Category category = new Category();
    category.setName(requestData.getName());
    category.setExpense(requestData.getExpense());
    category.setIcon(requestData.getIcon());
    category.setColor(requestData.getColor());
    category.setActive(true);

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
    walletData.setId(wallet.getId());
    walletData.setName(wallet.getName());
    walletData.setDefaultCurrencyCode(wallet.getDefaultCurrencyCode());
    walletData.setDefaultWallet(wallet.isDefaultWallet());
    walletData.setInitialBalanceAmount(wallet.getInitialBalanceAmount());

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

    Set<Category> categories = requestData.getCategories()
        .stream()
        .map(categoryData -> toCategory(categoryData))
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

    if (requestData.getCategories() != null && !requestData.getCategories().isEmpty()) {
      Set<Category> categories = requestData.getCategories()
          .stream()
          .map(categoryData -> toCategory(categoryData))
          .collect(Collectors.toSet());
      wallet.setCategories(categories);
    }

    return wallet;
  }

}
