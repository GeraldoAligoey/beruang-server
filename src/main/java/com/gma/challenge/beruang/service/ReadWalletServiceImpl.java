package com.gma.challenge.beruang.service;

import java.util.List;

import com.gma.challenge.beruang.model.Wallet;
import com.gma.challenge.beruang.repo.WalletRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadWalletServiceImpl implements ReadWalletService {

  @Autowired
  WalletRepository walletRepo;

  @Override
  public Wallet findById(Long id) {
    return walletRepo.findById(id).get();
  }

  @Override
  public List<Wallet> findAll() {
    return walletRepo.findAll();
  }
}
