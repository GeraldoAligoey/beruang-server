package com.gma.challenge.beruang.util;

import com.gma.challenge.beruang.data.CategoryData;
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

}
