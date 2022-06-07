package com.gma.challenge.beruang.util;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.gma.challenge.beruang.data.CategoryData;
import com.gma.challenge.beruang.data.NewCategoryRequestData;
import com.gma.challenge.beruang.data.NewWalletData;
import com.gma.challenge.beruang.data.NewWalletRequestData;
import com.gma.challenge.beruang.data.UpdateCategoryData;
import com.gma.challenge.beruang.data.UpdateWalletData;
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

  public static Category toCategory(NewCategoryRequestData newCategoryData) {
    Category category = new Category();
    category.setName(newCategoryData.getName());
    category.setExpense(newCategoryData.getExpense());
    category.setIcon(newCategoryData.getIcon());
    category.setColor(newCategoryData.getColor());
    category.setActive(true);

    return category;
  }

  public static Category updateCategory(Category category, UpdateCategoryData categoryData) {
    category.setName(categoryData.getName());
    category.setExpense(categoryData.getExpense());
    category.setIcon(categoryData.getIcon());
    category.setColor(categoryData.getColor());

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

  public static Wallet toWallet(NewWalletRequestData newWalletRequestData) {
    NewWalletData newWalletData = newWalletRequestData.getWallet();
    Wallet wallet = new Wallet();
    wallet.setName(newWalletData.getName());
    wallet.setDefaultCurrencyCode(newWalletData.getDefaultCurrencyCode());

    Set<Category> categories = newWalletData.getCategories()
      .stream()
      .map(categoryData -> toCategory(categoryData))
      .collect(Collectors.toSet());
    
    wallet.setCategories(categories);

    if (newWalletData.getInitialBalanceAmount() == null) {
      wallet.setInitialBalanceAmount(BigDecimal.ZERO);
    } else {
      wallet.setInitialBalanceAmount(newWalletData.getInitialBalanceAmount());
    }

    if (newWalletData.getDefaultWallet() != null) {
      wallet.setDefaultWallet(newWalletData.getDefaultWallet());
    }

    return wallet;
  }

  public static Wallet updateWallet(Wallet wallet, UpdateWalletData updateWalletData) {
    if (updateWalletData.getName() != null && !updateWalletData.getName().isBlank()) {
      wallet.setName(updateWalletData.getName());
    }

    if (updateWalletData.getDefaultWallet() != null) {
      wallet.setDefaultWallet(updateWalletData.getDefaultWallet());
    }

    if (updateWalletData.getDefaultCurrencyCode() != null && !updateWalletData.getDefaultCurrencyCode().isBlank()) {
      wallet.setDefaultCurrencyCode(updateWalletData.getDefaultCurrencyCode());
    }

    if (updateWalletData.getInitialBalanceAmount() != null) {
      wallet.setInitialBalanceAmount(updateWalletData.getInitialBalanceAmount());
    }

    if (updateWalletData.getCategories() != null && !updateWalletData.getCategories().isEmpty()) {
      Set<Category> categories = updateWalletData.getCategories()
        .stream()
        .map(categoryData -> toCategory(categoryData))
        .collect(Collectors.toSet());
      wallet.setCategories(categories);
    }

    return wallet;
  }

}
