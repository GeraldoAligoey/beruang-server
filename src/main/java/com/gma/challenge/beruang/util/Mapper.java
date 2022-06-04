package com.gma.challenge.beruang.util;

import java.util.List;
import java.util.stream.Collectors;

import com.gma.challenge.beruang.data.CategoryData;
import com.gma.challenge.beruang.data.NewCategoryRequestData;
import com.gma.challenge.beruang.data.UpdateCategoryData;
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
}
