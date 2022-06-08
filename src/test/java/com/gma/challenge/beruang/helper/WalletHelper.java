package com.gma.challenge.beruang.helper;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.gma.challenge.beruang.data.CategoryData;
import com.gma.challenge.beruang.data.NewWalletRequestData;
import com.gma.challenge.beruang.data.WalletData;
import com.gma.challenge.beruang.data.WalletResponseData;

public class WalletHelper {

  private static final BigDecimal WALLET_INITIALBALANCE = new BigDecimal(1000);
  private static final boolean WALLET_DEFAULTWALLET = true;
  private static final String WALLET_CURRENCYCODE = "MYR";
  private static final String WALLET_NAME = "My Wallet";

  public static NewWalletRequestData getValidNewWalletRequestDataSample() {

    NewWalletRequestData requestData = new NewWalletRequestData();
    requestData.setName(WALLET_NAME);
    requestData.setDefaultCurrencyCode(WALLET_CURRENCYCODE);
    requestData.setDefaultWallet(WALLET_DEFAULTWALLET);
    requestData.setInitialBalanceAmount(WALLET_INITIALBALANCE);
    requestData.setCategoryIds(CategoryHelper.getCategoryIdsDataSample());

    return requestData;
  }

  public static boolean isWalletResponseDataEqualsToSample(WalletResponseData responseData) {
    WalletData walletData = responseData.getWallet();

    if (!walletData.getName().equals(WALLET_NAME)) {
      return false;
    }

    if (!walletData.getDefaultCurrencyCode().equals(WALLET_CURRENCYCODE)) {
      return false;
    }

    if (!walletData.getDefaultWallet().equals(WALLET_DEFAULTWALLET)) {
      return false;
    }

    if (!walletData.getInitialBalanceAmount().equals(WALLET_INITIALBALANCE)) {
      return false;
    }

    if (!isEquals(walletData, CategoryHelper.getCategoryIdsDataSample())) {
      return false;
    }

    return true;
  }

  private static boolean isEquals(WalletData walletData, List<Long> categoryIdsDataSample) {
    List<CategoryData> categories = walletData.getCategories();

    List<Long> categoryIds = categories.stream().map(category -> category.getId()).collect(Collectors.toList());

    return categoryIds.containsAll(categoryIdsDataSample);
  }

}
