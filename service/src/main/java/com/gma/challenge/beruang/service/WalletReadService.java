package com.gma.challenge.beruang.service;

import com.gma.challenge.beruang.generated.dto.WalletResponseData;
import com.gma.challenge.beruang.generated.dto.WalletsResponseData;

public interface WalletReadService {

    public WalletResponseData findWallet(Long walletId);

    public WalletsResponseData findWallets();
}
