package com.gma.challenge.beruang.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.gma.challenge.beruang.model.Wallet;

public class WalletDataSample implements DataSampleService<Wallet> {

  @Override
  public Wallet getSample() {
    Wallet wallet = new Wallet();

    wallet.setDefaultCurrencyCode("MYR");
    wallet.setDefaultWallet(true);
    wallet.setName("Test Wallet 001");
    wallet.setBalanceAmount(new BigDecimal(1000));
    wallet.setCategories(new TransactionCategoryDataSample().getSamples());

    return wallet;
  }

  @Override
  public List<Wallet> getSamples() {
    List<Wallet> wallets = new ArrayList<>();

    Wallet walletMyr = new Wallet();
    walletMyr.setDefaultCurrencyCode("MYR");
    walletMyr.setDefaultWallet(true);
    walletMyr.setName("Test Wallet 001");
    walletMyr.setBalanceAmount(new BigDecimal(1000));
    walletMyr.setCategories(new TransactionCategoryDataSample().getSamples());

    wallets.add(walletMyr);

    Wallet walletUsd = new Wallet();
    walletUsd.setDefaultCurrencyCode("USD");
    walletUsd.setDefaultWallet(false);
    walletUsd.setName("Test Wallet 002");
    walletUsd.setBalanceAmount(new BigDecimal(3000));
    walletUsd.setCategories(new TransactionCategoryDataSample().getSamples());
    
    wallets.add(walletUsd);

    return wallets;
  }
}
