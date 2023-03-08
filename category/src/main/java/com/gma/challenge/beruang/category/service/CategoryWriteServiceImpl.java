package com.gma.challenge.beruang.category.service;

import com.gma.challenge.beruang.category.model.Category;
import com.gma.challenge.beruang.category.repo.CategoryRepository;
import com.gma.challenge.beruang.category.util.Mapper;
import com.gma.challenge.beruang.category.util.Validator;
import com.gma.challenge.beruang.common.exception.CategoryNotFoundException;
import com.gma.challenge.beruang.generated.dto.CategoryData;
import com.gma.challenge.beruang.generated.dto.CategoryResponseData;
import com.gma.challenge.beruang.generated.dto.NewCategoryRequestData;
import com.gma.challenge.beruang.generated.dto.UpdateCategoryRequestData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service
public class CategoryWriteServiceImpl implements CategoryWriteService {

    private final CategoryRepository categoryRepository;

    public CategoryWriteServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponseData createCategory(NewCategoryRequestData newCategoryRequestData) {
        Validator.validateNewCategoryRequestData(newCategoryRequestData);
        Category category =
                categoryRepository.saveAndFlush(Mapper.toCategory(newCategoryRequestData));

        return CategoryResponseData.builder().category(Mapper.toCategoryData(category)).build();
    }

    @Override
    public CategoryResponseData updateCategory(
            Long id, UpdateCategoryRequestData updateCategoryRequestData) {
        Validator.validateUpdateCategoryRequestData(updateCategoryRequestData);

        try {
            Category category = categoryRepository.getReferenceById(id);
            CategoryData categoryData =
                    Mapper.toCategoryData(
                            categoryRepository.saveAndFlush(
                                    Mapper.updateCategory(category, updateCategoryRequestData)));

            return CategoryResponseData.builder().category(categoryData).build();
        } catch (EntityNotFoundException ex) {
            throw new CategoryNotFoundException("Invalid category id");
        }
    }

    @Override
    public void deleteCategory(Long id) {
        Category category =
                categoryRepository
                        .findById(id)
                        .orElseThrow(() -> new CategoryNotFoundException("Invalid category id"));

        categoryRepository.delete(category);
    }
}
