package com.gma.challenge.beruang.service;


import com.gma.challenge.beruang.data.CategoryData;
import com.gma.challenge.beruang.data.CategoryResponseData;
import com.gma.challenge.beruang.data.NewCategoryRequestData;
import com.gma.challenge.beruang.data.UpdateCategoryData;
import com.gma.challenge.beruang.data.UpdateCategoryRequestData;
import com.gma.challenge.beruang.domain.Category;
import com.gma.challenge.beruang.repo.CategoryRepository;
import com.gma.challenge.beruang.util.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CategoryWriteServiceImpl implements CategoryWriteService {

  private final CategoryRepository categoryRepository;

  @Autowired
  public CategoryWriteServiceImpl(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Override
  public CategoryResponseData createCategory(NewCategoryRequestData newCategoryRequestData) {
    Category category = categoryRepository.saveAndFlush(Mapper.toCategory(newCategoryRequestData));

    CategoryResponseData categoryResponseData = new CategoryResponseData();
    categoryResponseData.category(Mapper.toCategoryData(category));

    return categoryResponseData;
  }

  @Override
  public CategoryResponseData updateCategory(Long id, UpdateCategoryRequestData updateCategoryRequestData) {
    UpdateCategoryData updateCategoryData = updateCategoryRequestData.getCategory();

    Category category = categoryRepository.getReferenceById(id);
    CategoryData categoryData = Mapper.toCategoryData(categoryRepository.saveAndFlush(Mapper.updateCategory(category, updateCategoryData)));

    CategoryResponseData categoryResponseData = new CategoryResponseData();
    categoryResponseData.category(categoryData);

    return categoryResponseData;
  }

  @Override
  public void deleteCategory(Long id) {
    categoryRepository.delete(categoryRepository.getReferenceById(id));
  }
  
}
