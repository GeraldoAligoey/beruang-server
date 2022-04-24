package com.gma.challenge.beruang.serviceImpl;

import java.util.List;
import java.util.Set;

import com.gma.challenge.beruang.model.Category;
import com.gma.challenge.beruang.repo.CategoryRepository;
import com.gma.challenge.beruang.service.ReadCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadCategoryServiceImpl implements ReadCategoryService {

  @Autowired
  CategoryRepository categoryRepository;

  @Override
  public Category findById(Long id) {

    return categoryRepository.findById(id).get();
  }

  @Override
  public List<Category> findAll() {

    return categoryRepository.findAll();
  }

  @Override
  public Set<Category> findAllByWalletId(Long walletId) {

    return categoryRepository.findByWalletId(walletId);
  }

  @Override
  public Set<Category> findAllByBudgetId(Long budgetId) {

    return categoryRepository.findByBudgetId(budgetId);
  }

}
