package com.gma.challenge.beruang.wallet.service;

import com.gma.challenge.beruang.generated.dto.NewWalletRequestData;
import com.gma.challenge.beruang.generated.dto.UpdateWalletRequestData;
import com.gma.challenge.beruang.generated.dto.WalletResponseData;

public interface WalletWriteService {

    public WalletResponseData createWallet(NewWalletRequestData newWalletRequestData);

    public WalletResponseData updateWallet(
            Long walletId, UpdateWalletRequestData updateWalletRequestData);

    public void deleteWallet(Long walletId);

    public WalletResponseData setDefaultTrue(Long walletId);

//    public void moveTransactionToNewCategory(Long walletId, Long oldCategoryId, Long newCategoryId);
//
//    public void moveTransactionToNewWallet(Long oldWalletId, Long newWalletId);
}
