package com.gma.challenge.beruang.service;

import java.util.List;
import java.util.Set;

import com.gma.challenge.beruang.model.Category;

public interface ReadCategoryService {

  public Category findById(Long id);
  
  public List<Category> findAll();

  public Set<Category> findAllByWalletId(Long walletId);

  public Set<Category> findAllByBudgetId(Long budgetId);
}
