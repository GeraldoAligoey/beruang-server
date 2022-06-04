package com.gma.challenge.beruang.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gma.challenge.beruang.data.WalletData;
import com.gma.challenge.beruang.data.WalletResponseData;
import com.gma.challenge.beruang.data.WalletsResponseData;
import com.gma.challenge.beruang.domain.Wallet;
import com.gma.challenge.beruang.repo.WalletRepository;
import com.gma.challenge.beruang.util.Mapper;

@Service
public class WalletReadServiceImpl implements WalletReadService {

  @Autowired
  WalletRepository walletRepository;

  @Override
  public WalletResponseData findWallet(Long walletId) {
    Wallet wallet = walletRepository.findById(walletId).orElse(null);
    WalletResponseData responseData = new WalletResponseData();
    responseData.setWallet(Mapper.toWalletData(wallet));

    return responseData;
  }

  @Override
  public WalletsResponseData findWallets() {
    List<Wallet> wallets = walletRepository.findAll();
    WalletsResponseData responseData = new WalletsResponseData();
    List<WalletData> walletDatas = wallets.stream().map(wallet -> Mapper.toWalletData(wallet)).collect(Collectors.toList());
    responseData.setWallets(walletDatas);

    return responseData;
  }

}
