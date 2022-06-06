package com.gma.challenge.beruang.service;

import com.gma.challenge.beruang.data.CategoriesResponseData;
import com.gma.challenge.beruang.data.CategoryResponseData;

public interface CategoryReadService {

  public CategoriesResponseData findCategories();

  public CategoryResponseData findCategory(Long categoryId);
}
