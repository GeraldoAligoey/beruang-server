package com.gma.challenge.beruang.common.helper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import com.gma.challenge.beruang.data.CategoryData;
import com.gma.challenge.beruang.data.NewWalletRequestData;
import com.gma.challenge.beruang.data.UpdateWalletRequestData;
import com.gma.challenge.beruang.data.WalletData;
import com.gma.challenge.beruang.data.WalletResponseData;
import com.gma.challenge.beruang.data.WalletsResponseData;
import com.gma.challenge.beruang.domain.Category;
import com.gma.challenge.beruang.domain.Wallet;

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

  public static List<WalletData> getWalletDataSamples() {
    List<WalletData> wallets = new ArrayList<>();

    wallets.add(WalletData.builder()
      .id(Long.valueOf(1))
      .name("Spending")
      .defaultCurrencyCode("MYR")
      .initialBalanceAmount(new BigDecimal(10000))
      .defaultWallet(true)
      .categories(CategoryHelper.getCategoryDataSamples(true))
      .build());

    wallets.add(WalletData.builder()
      .id(Long.valueOf(2))
      .name("Saving")
      .defaultCurrencyCode("MYR")
      .initialBalanceAmount(new BigDecimal(50000))
      .defaultWallet(true)
      .categories(CategoryHelper.getCategoryDataSamples(false))
      .build());

    return wallets;
  }

  public static WalletsResponseData getWalletsResponseDataSample() {
    return WalletsResponseData.builder().wallets(getWalletDataSamples()).build();
  }

  public static WalletsResponseData getWalletsResponseDataSingleSample() {
    List<WalletData> walletDataSamples = getWalletDataSamples();
    walletDataSamples.remove(1);

    return WalletsResponseData.builder().wallets(walletDataSamples).build();
  }

  public static WalletsResponseData getWalletsResponseDataEmptySample() {
    List<WalletData> wallets = new ArrayList<>();

    return WalletsResponseData.builder().wallets(wallets).build();
  }

  public static Wallet getWalletSample() {
    Wallet wallet = new Wallet();
    wallet.setId(Long.valueOf(1));
    wallet.setName("Spending");
    wallet.setDefaultCurrencyCode("MYR");
    wallet.setDefaultWallet(true);
    wallet.setInitialBalanceAmount(BigDecimal.valueOf(1000));
    
    List<Category> categorySamples = CategoryHelper.getCategorySamples(false);
    HashSet<Category> categorySet = new HashSet<>(categorySamples);

    wallet.setCategories(categorySet);

    return wallet;
  }

  public static List<Wallet> getWalletSamples() {
    List<Wallet> wallets = new ArrayList<>();

    Wallet w1 = new Wallet();
    w1.setId(Long.valueOf(1));
    w1.setName("Spending");
    w1.setDefaultCurrencyCode("MYR");
    w1.setDefaultWallet(true);
    w1.setInitialBalanceAmount(BigDecimal.valueOf(1000));

    Wallet w2 = new Wallet();
    w2.setId(Long.valueOf(2));
    w2.setName("Saving");
    w2.setDefaultCurrencyCode("MYR");
    w2.setDefaultWallet(false);
    w2.setInitialBalanceAmount(BigDecimal.valueOf(5000));

    wallets.add(w1);
    wallets.add(w2);

    return wallets;
  }

}
