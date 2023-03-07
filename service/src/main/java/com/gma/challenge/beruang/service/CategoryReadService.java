package com.gma.challenge.beruang.service;

import com.gma.challenge.beruang.generated.dto.CategoriesResponseData;
import com.gma.challenge.beruang.generated.dto.CategoryResponseData;

public interface CategoryReadService {
    public CategoriesResponseData findCategories();

    public CategoryResponseData findCategory(Long categoryId);
}
