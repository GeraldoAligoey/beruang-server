package com.gma.challenge.beruang.service;

import java.util.List;

import com.gma.challenge.beruang.model.Category;

public interface ReadCategoryService {

  public Category findById(Long id);
  
  public List<Category> findAll();

  public List<Category> findAllByWalletId(Long walletId);

  public List<Category> findAllByBudgetId(Long budgetId);
}
