package com.gma.challenge.beruang.service;

import com.gma.challenge.beruang.dto.CategoryData;
import com.gma.challenge.beruang.model.Category;
import com.gma.challenge.beruang.repo.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WriteCategoryServiceImpl implements WriteCategoryService {

  @Autowired
  private CategoryRepository categoryRepository;


  @Override
  public Category updateCategoryById(Long id, Category category) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void deleteCategoryById(Long categoryId) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Category createCategory(CategoryData categoryData) {
    boolean isExpense = categoryData.getType().equals("expense") ? true : false; 
    String name = categoryData.getName();
    String icon = categoryData.getIcon();
    String color = categoryData.getColor();

    return createCategory(isExpense, name, icon, color, true);
  }

  public Category createCategory(boolean isExpense, String name, String icon, String color, boolean isUserDefined) {
    Category category = new Category(name, isExpense, icon, color, isUserDefined);

    return categoryRepository.save(category);
  }
  
}
