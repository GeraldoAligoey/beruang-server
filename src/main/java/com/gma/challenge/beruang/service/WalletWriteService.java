package com.gma.challenge.beruang.service;

import com.gma.challenge.beruang.data.NewWalletRequestData;
import com.gma.challenge.beruang.data.UpdateWalletRequestData;
import com.gma.challenge.beruang.data.WalletResponseData;

public interface WalletWriteService {
  
  public WalletResponseData createWallet(NewWalletRequestData newWalletRequestData);

  public WalletResponseData updateWallet(Long walletId, UpdateWalletRequestData updateWalletRequestData);

  public void deleteWallet(Long walletId);

  public WalletResponseData setDefaultTrue(Long walletId);

}