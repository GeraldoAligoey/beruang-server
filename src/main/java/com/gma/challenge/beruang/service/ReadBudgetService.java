package com.gma.challenge.beruang.service;

import java.util.List;

import com.gma.challenge.beruang.model.Budget;

public interface ReadBudgetService {
  
  public Budget findById(Long id);

  public Budget findByWalletId(Long walletId);

  public List<Budget> findAll();
}
