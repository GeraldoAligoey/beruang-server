package com.gma.challenge.beruang.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gma.challenge.beruang.data.WalletResponseData;
import com.gma.challenge.beruang.data.WalletsResponseData;
import com.gma.challenge.beruang.domain.Wallet;
import com.gma.challenge.beruang.exception.WalletNotFoundException;
import com.gma.challenge.beruang.repo.WalletRepository;
import com.gma.challenge.beruang.util.Mapper;

@Transactional
@Service
public class WalletReadServiceImpl implements WalletReadService {

  private final WalletRepository walletRepository;

  @Autowired
  public WalletReadServiceImpl(WalletRepository walletRepository) {
    this.walletRepository = walletRepository;
  }

  @Override
  public WalletResponseData findWallet(Long walletId) {
    Wallet wallet = walletRepository.findById(walletId)
        .orElseThrow(() -> new WalletNotFoundException("Wallet id " + walletId + " not found"));

    return WalletResponseData.builder().wallet(Mapper.toWalletData(wallet)).build();
  }

  @Override
  public WalletsResponseData findWallets() {
    List<Wallet> wallets = walletRepository.findAll();

    return WalletsResponseData.builder().wallets(
        wallets
            .stream()
            .map(wallet -> Mapper.toWalletData(wallet))
            .collect(Collectors.toList()))
        .build();
  }

}
