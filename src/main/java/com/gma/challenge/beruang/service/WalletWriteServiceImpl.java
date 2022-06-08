package com.gma.challenge.beruang.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gma.challenge.beruang.data.NewWalletRequestData;
import com.gma.challenge.beruang.data.UpdateWalletRequestData;
import com.gma.challenge.beruang.data.WalletData;
import com.gma.challenge.beruang.data.WalletResponseData;
import com.gma.challenge.beruang.domain.Wallet;
import com.gma.challenge.beruang.exception.WalletNotFoundException;
import com.gma.challenge.beruang.repo.WalletRepository;
import com.gma.challenge.beruang.util.Mapper;
import com.gma.challenge.beruang.util.Validator;

@Transactional
@Service
public class WalletWriteServiceImpl implements WalletWriteService {

  private final WalletRepository walletRepository;

  @Autowired
  public WalletWriteServiceImpl(WalletRepository walletRepository) {
    this.walletRepository = walletRepository;
  }

  @Override
  public WalletResponseData createWallet(NewWalletRequestData newWalletRequestData) {
    Validator.validateNewWalletRequestData(newWalletRequestData);
    Wallet wallet = walletRepository.saveAndFlush(Mapper.toWallet(newWalletRequestData));

    WalletResponseData walletResponseData = new WalletResponseData();
    walletResponseData.wallet(Mapper.toWalletData(wallet));

    return walletResponseData;
  }

  @Override
  public WalletResponseData updateWallet(Long walletId, UpdateWalletRequestData updateWalletRequestData) {
    try {
      Validator.validateUpdateWalletRequestData(updateWalletRequestData);

      Wallet wallet = walletRepository.getReferenceById(walletId);
      WalletData walletData = Mapper.toWalletData(
          walletRepository.saveAndFlush(Mapper.updateWallet(wallet, updateWalletRequestData)));
      WalletResponseData responseData = new WalletResponseData();
      responseData.setWallet(walletData);

      return responseData;
    } catch (EntityNotFoundException ex) {
      throw new WalletNotFoundException("Invalid wallet id");
    }
  }

  @Override
  public void deleteWallet(Long walletId) {
    Wallet wallet = walletRepository.findById(walletId).orElseThrow(() -> new WalletNotFoundException("Wallet id " + walletId + " not found"));
    walletRepository.delete(wallet);
  }

}