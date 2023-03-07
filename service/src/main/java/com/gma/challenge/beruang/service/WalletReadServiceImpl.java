package com.gma.challenge.beruang.service;

import com.gma.challenge.beruang.exception.WalletNotFoundException;
import com.gma.challenge.beruang.generated.dto.WalletResponseData;
import com.gma.challenge.beruang.generated.dto.WalletsResponseData;
import com.gma.challenge.beruang.model.Wallet;
import com.gma.challenge.beruang.repo.WalletRepository;
import com.gma.challenge.beruang.service.util.Mapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
