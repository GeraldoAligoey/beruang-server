package com.gma.challenge.beruang.wallet.service;

import com.gma.challenge.beruang.common.exception.WalletNotFoundException;
import com.gma.challenge.beruang.generated.dto.WalletResponseData;
import com.gma.challenge.beruang.generated.dto.WalletsResponseData;
import com.gma.challenge.beruang.wallet.model.Wallet;
import com.gma.challenge.beruang.wallet.repo.WalletRepository;
import com.gma.challenge.beruang.wallet.util.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class WalletReadServiceImpl implements WalletReadService {

    private final WalletRepository walletRepository;

    public WalletReadServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public WalletResponseData findWallet(Long walletId) {
        Wallet wallet =
                walletRepository
                        .findById(walletId)
                        .orElseThrow(
                                () ->
                                        new WalletNotFoundException(
                                                "Wallet id " + walletId + " not found"));

        return WalletResponseData.builder().wallet(Mapper.toWalletData(wallet)).build();
    }

    @Override
    public WalletsResponseData findWallets() {
        List<Wallet> wallets = walletRepository.findAll();

        return WalletsResponseData.builder()
                .wallets(
                        wallets.stream()
                                .map(wallet -> Mapper.toWalletData(wallet))
                                .collect(Collectors.toList()))
                .build();
    }
}
