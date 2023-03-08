package com.gma.challenge.beruang.service;

import com.gma.challenge.beruang.exception.CategoryNotFoundException;
import com.gma.challenge.beruang.generated.dto.CategoriesResponseData;
import com.gma.challenge.beruang.generated.dto.CategoryResponseData;
import com.gma.challenge.beruang.model.Category;
import com.gma.challenge.beruang.repo.CategoryRepository;
import com.gma.challenge.beruang.service.util.Mapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class CategoryReadServiceImpl implements CategoryReadService {

    private final CategoryRepository categoryRepository;

    public CategoryReadServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoriesResponseData findCategories() {
        List<Category> categories = categoryRepository.findAll();

        return CategoriesResponseData.builder()
                .categories(Mapper.toCategoriesData(categories))
                .build();
    }

    @Override
    public CategoryResponseData findCategory(Long categoryId) {
        Category category =
                categoryRepository
                        .findById(categoryId)
                        .orElseThrow(
                                () ->
                                        new CategoryNotFoundException(
                                                "Category id " + categoryId + " not found"));

        return CategoryResponseData.builder().category(Mapper.toCategoryData(category)).build();
    }
}
