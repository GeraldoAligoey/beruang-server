package com.gma.challenge.beruang.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gma.challenge.beruang.data.CategoryData;
import com.gma.challenge.beruang.data.CategoryResponseData;
import com.gma.challenge.beruang.data.NewCategoryRequestData;
import com.gma.challenge.beruang.data.UpdateCategoryRequestData;
import com.gma.challenge.beruang.domain.Category;
import com.gma.challenge.beruang.exception.CategoryNotFoundException;
import com.gma.challenge.beruang.repo.CategoryRepository;
import com.gma.challenge.beruang.util.Mapper;
import com.gma.challenge.beruang.util.Validator;

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
    Validator.validateNewCategoryRequestData(newCategoryRequestData);
    Category category = categoryRepository.saveAndFlush(Mapper.toCategory(newCategoryRequestData));

    return new CategoryResponseData().category(Mapper.toCategoryData(category));
  }

  @Override
  public CategoryResponseData updateCategory(Long id, UpdateCategoryRequestData updateCategoryRequestData) {
    Validator.validateUpdateCategoryRequestData(updateCategoryRequestData);

    try {
      Category category = categoryRepository.getReferenceById(id);
      CategoryData categoryData = Mapper
          .toCategoryData(categoryRepository.saveAndFlush(Mapper.updateCategory(category, updateCategoryRequestData)));

      return new CategoryResponseData().category(categoryData);
    } catch (EntityNotFoundException ex) {
      throw new CategoryNotFoundException("Invalid category id");
    }
  }

  @Override
  public void deleteCategory(Long id) {
    Category category = categoryRepository.findById(id)
        .orElseThrow(() -> new CategoryNotFoundException("Invalid category id"));

    categoryRepository.delete(category);
  }

}
