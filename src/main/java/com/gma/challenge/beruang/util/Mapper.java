package com.gma.challenge.beruang.util;

import com.gma.challenge.beruang.data.CategoryData;
import com.gma.challenge.beruang.data.NewCategoryRequestData;
import com.gma.challenge.beruang.data.UpdateCategoryData;
import com.gma.challenge.beruang.domain.Category;

public class Mapper {

  public static CategoryData toCategoryData(Category category) {
    CategoryData categoryData = new CategoryData();
    categoryData.setId(category.getId());
    categoryData.setName(category.getName());
    categoryData.setExpense(category.isExpense());
    categoryData.setIcon(category.getIcon());
    categoryData.setColor(category.getColor());
    categoryData.setActive(category.isActive());

    return categoryData;
  }

  public static Category toCategory(NewCategoryRequestData newCategoryData) {
    Category category = new Category();
    category.setName(newCategoryData.getName());
    category.setExpense(newCategoryData.getExpense());
    category.setIcon(newCategoryData.getIcon());
    category.setColor(newCategoryData.getColor());
    category.setActive(true);

    return category;

  }

  public static Category updateCategory(Category category, UpdateCategoryData categoryData) {
    category.setName(categoryData.getName());
    category.setExpense(categoryData.getExpense());
    category.setIcon(categoryData.getIcon());
    category.setColor(categoryData.getColor());

    return category;
  }

}
