package com.gma.challenge.beruang.service;

import com.gma.challenge.beruang.dto.CategoryData;
import com.gma.challenge.beruang.model.Category;

public interface WriteCategoryService {

  public Category createCategory(CategoryData categoryData);

  public Category updateCategoryById(Long id, Category category);

  public void deleteCategoryById(Long categoryId);
}
