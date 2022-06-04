package com.gma.challenge.beruang.service;

import com.gma.challenge.beruang.data.WalletResponseData;
import com.gma.challenge.beruang.data.WalletsResponseData;

public interface WalletReadService {

  public WalletResponseData findWallet(Long walletId);

  public WalletsResponseData findWallets();
  
}
