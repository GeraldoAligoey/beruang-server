package com.gma.challenge.beruang.service;

import java.util.List;

import com.gma.challenge.beruang.model.Wallet;

public interface ReadWalletService {

  public Wallet findById(Long id);
  
  public List<Wallet> findAll();
}
