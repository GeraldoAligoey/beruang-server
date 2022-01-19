package com.gma.challenge.beruang.service;

import com.gma.challenge.beruang.model.Category;

public interface WriteCategoryService {

  public Category createCategory(boolean isExpense, String icon, String color,
      boolean isUserDefined, boolean isActive);

  public Category updateCategoryById(Long id, Category category);

  public void deleteCategoryById(Long categoryId);
}
