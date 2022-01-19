package com.gma.challenge.beruang.service;

import java.math.BigDecimal;
import java.util.List;

import com.gma.challenge.beruang.model.Category;
import com.gma.challenge.beruang.model.Wallet;

public interface WriteWalletService {

  public Wallet createWallet(List<Category> categories, String currencyCode);

  public Wallet createWallet(List<Category> categories, String currencyCode, BigDecimal startingAmount);

  public Wallet createWallet(List<Category> categories, String currencyCode, BigDecimal startingAmount,
      boolean isDefaultWallet);

  public Wallet updateWalletById(Long id, Wallet wallet);

  public void deleteWalletById(Long walletId);
}
