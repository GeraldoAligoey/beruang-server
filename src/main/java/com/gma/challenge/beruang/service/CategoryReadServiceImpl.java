package com.gma.challenge.beruang.service;

import java.util.List;

import com.gma.challenge.beruang.data.CategoriesResponseData;
import com.gma.challenge.beruang.data.CategoryResponseData;
import com.gma.challenge.beruang.domain.Category;
import com.gma.challenge.beruang.exception.CategoryNotFoundException;
import com.gma.challenge.beruang.repo.CategoryRepository;
import com.gma.challenge.beruang.util.Mapper;

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
      data.addCategoriesItem(Mapper.toCategoryData(category));
    }

    return data;
  }

  @Override
  public CategoryResponseData findCategory(Long id) {
    Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found"));

    CategoryResponseData data = new CategoryResponseData();

    data.category(Mapper.toCategoryData(category));

    return data;
  }
  
}
