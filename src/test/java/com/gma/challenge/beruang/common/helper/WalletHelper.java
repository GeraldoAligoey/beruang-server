package com.gma.challenge.beruang.common.helper;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.gma.challenge.beruang.data.CategoryData;
import com.gma.challenge.beruang.data.NewWalletRequestData;
import com.gma.challenge.beruang.data.UpdateWalletRequestData;
import com.gma.challenge.beruang.data.WalletData;
import com.gma.challenge.beruang.data.WalletResponseData;

public class WalletHelper {

  private static final BigDecimal WALLET_INITIALBALANCE = new BigDecimal(1000);
  private static final boolean WALLET_DEFAULTWALLET = false;
  private static final String WALLET_CURRENCYCODE = "MYR";
  private static final String WALLET_NAME = "My Wallet";

  private static final List<Long> UPDATE_WALLET_CATEGORYIDS = Arrays.asList(Long.valueOf(5));
  private static final BigDecimal UPDATE_WALLET_INITIALBALANCE = new BigDecimal(5000);
  private static final boolean UPDATE_WALLET_DEFAULTWALLET = true;
  private static final String UPDATE_WALLET_CURRENCYCODE = "JPY";
  private static final String UPDATE_WALLET_NAME = "Updated Wallet Name";

  public static NewWalletRequestData getValidNewWalletRequestDataSample() {
    NewWalletRequestData requestData = new NewWalletRequestData();
    requestData.setName(WALLET_NAME);
    requestData.setDefaultCurrencyCode(WALLET_CURRENCYCODE);
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

  public static NewWalletRequestData getInvalidIncompleteNewWalletRequestDataSample() {
    NewWalletRequestData requestData = new NewWalletRequestData();
    requestData.setName(WALLET_NAME);
    requestData.setDefaultCurrencyCode(WALLET_CURRENCYCODE);

    return requestData;
  }

  public static UpdateWalletRequestData getValidFullUpdateRequestDataSample() {
    UpdateWalletRequestData requestData = new UpdateWalletRequestData();
    requestData.setName(UPDATE_WALLET_NAME);
    requestData.setDefaultCurrencyCode(UPDATE_WALLET_CURRENCYCODE);
    requestData.setInitialBalanceAmount(UPDATE_WALLET_INITIALBALANCE);
    requestData.setCategoryIds(UPDATE_WALLET_CATEGORYIDS);
    return requestData;
  }

  public static boolean isWalletUpdatedFullResponseDataEqualsToSample(WalletResponseData responseData) {
    WalletData walletData = responseData.getWallet();

    if (!walletData.getName().equals(UPDATE_WALLET_NAME)) {
      return false;
    }

    if (!walletData.getDefaultWallet().equals(UPDATE_WALLET_DEFAULTWALLET)) {
      return false;
    }

    if (!walletData.getDefaultCurrencyCode().equals(UPDATE_WALLET_CURRENCYCODE)) {
      return false;
    }

    if (!walletData.getInitialBalanceAmount().equals(UPDATE_WALLET_INITIALBALANCE)) {
      return false;
    }

    List<CategoryData> categories = walletData.getCategories();
    List<Long> categoryIds = categories.stream().map(category -> category.getId()).collect(Collectors.toList());

    if (!categoryIds.containsAll(UPDATE_WALLET_CATEGORYIDS)) {
      return false;
    }

    return true;
  }

  public static UpdateWalletRequestData getValidPartialUpdateRequestDataSample() {
    UpdateWalletRequestData requestData = new UpdateWalletRequestData();
    requestData.setName(UPDATE_WALLET_NAME);
    requestData.setInitialBalanceAmount(UPDATE_WALLET_INITIALBALANCE);
    requestData.setCategoryIds(UPDATE_WALLET_CATEGORYIDS);
    return requestData;
  }

  public static boolean isWalletUpdatedPartialResponseDataEqualsToSample(WalletResponseData responseData) {
    WalletData walletData = responseData.getWallet();

    if (!walletData.getName().equals(UPDATE_WALLET_NAME)) {
      return false;
    }

    if (!walletData.getInitialBalanceAmount().equals(UPDATE_WALLET_INITIALBALANCE)) {
      return false;
    }

    List<CategoryData> categories = walletData.getCategories();
    List<Long> categoryIds = categories.stream().map(category -> category.getId()).collect(Collectors.toList());

    if (!categoryIds.containsAll(UPDATE_WALLET_CATEGORYIDS)) {
      return false;
    }

    return true;
  }

  public static UpdateWalletRequestData getInvalidEmptyUpdateRequestDataSample() {
    UpdateWalletRequestData requestData = new UpdateWalletRequestData();
    return requestData;
  }

  public static UpdateWalletRequestData getInvalidNullUpdateRequestDataSample() {
    return null;
  }

  public static NewWalletRequestData getInvalidEmptyNewWalletRequestDataSample() {
    NewWalletRequestData requestData = new NewWalletRequestData();
    return requestData;
  }

  public static NewWalletRequestData getInvalidNullNewWalletRequestDataSample() {
    return null;
  }

  public static UpdateWalletRequestData getValidPartialNewCategoriesAddUpdateRequestDataSample() {
    UpdateWalletRequestData requestData = UpdateWalletRequestData.builder()
      .categoryIds(Arrays.asList(Long.valueOf(1), Long.valueOf(2), Long.valueOf(3), Long.valueOf(4), Long.valueOf(5), Long.valueOf(6))).build();
    return requestData;
  }

  public static UpdateWalletRequestData getValidPartialNewCategoriesRemoveUpdateRequestDataSample() {
    UpdateWalletRequestData requestData = UpdateWalletRequestData.builder()
      .categoryIds(Arrays.asList(Long.valueOf(5), Long.valueOf(6))).build();
    return requestData;
  }

}
