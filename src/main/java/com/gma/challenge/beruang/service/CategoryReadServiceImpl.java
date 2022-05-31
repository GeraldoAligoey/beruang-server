package com.gma.challenge.beruang.service;

import java.util.List;

import com.gma.challenge.beruang.data.CategoriesResponseData;
import com.gma.challenge.beruang.data.CategoryData;
import com.gma.challenge.beruang.data.CategoryResponseData;
import com.gma.challenge.beruang.domain.Category;
import com.gma.challenge.beruang.exception.CategoryNotFoundException;
import com.gma.challenge.beruang.repo.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryReadServiceImpl implements CategoryReadService {

  private final CategoryRepository categoryRepository;

  @Autowired
  public CategoryReadServiceImpl(CategoryRepository categoryRespository) {
    this.categoryRepository = categoryRespository;
  }

  @Override
  public CategoriesResponseData getCategories() {
    List<Category> categories = categoryRepository.findAll();

    CategoriesResponseData data = new CategoriesResponseData();

    for (Category category : categories) {
      CategoryData categoryData = new CategoryData();
      categoryData.setId(category.getId());
      categoryData.setName(category.getName());
      categoryData.setExpense(category.isExpense());
      categoryData.setIcon(category.getIcon());
      categoryData.setColor(category.getColor());
      categoryData.setActive(category.isActive());

      data.addCategoriesItem(categoryData);
    }

    return data;
  }

  @Override
  public CategoryResponseData findCategory(Long id) {
    Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found"));

    CategoryResponseData data = new CategoryResponseData();

    CategoryData categoryData = new CategoryData();
    categoryData.setId(category.getId());
    categoryData.setName(category.getName());
    categoryData.setExpense(category.isExpense());
    categoryData.setIcon(category.getIcon());
    categoryData.setColor(category.getColor());
    categoryData.setActive(category.isActive());

    data.category(categoryData);

    return data;
  }
  
}
