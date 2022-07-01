package com.gma.challenge.beruang.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gma.challenge.beruang.data.CategoriesResponseData;
import com.gma.challenge.beruang.data.CategoryResponseData;
import com.gma.challenge.beruang.domain.Category;
import com.gma.challenge.beruang.exception.CategoryNotFoundException;
import com.gma.challenge.beruang.repo.CategoryRepository;
import com.gma.challenge.beruang.util.Mapper;

@Service
public class CategoryReadServiceImpl implements CategoryReadService {

  private final CategoryRepository categoryRepository;

  public CategoryReadServiceImpl(CategoryRepository categoryRespository) {
    this.categoryRepository = categoryRespository;
  }

  @Override
  public CategoriesResponseData findCategories() {
    List<Category> categories = categoryRepository.findAll();

    return CategoriesResponseData.builder().categories(Mapper.toCategoriesData(categories)).build();
  }

  @Override
  public CategoryResponseData findCategory(Long categoryId) {
    Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new CategoryNotFoundException("Category id " + categoryId + " not found"));
        
    return CategoryResponseData.builder().category(Mapper.toCategoryData(category)).build();
  }

}
