package com.gma.challenge.beruang.helper;

import java.util.List;
import java.util.stream.Collectors;

import com.gma.challenge.beruang.data.CategoryData;

public class UtilHelper {
  public static boolean isEquals(List<CategoryData> categoriesData, List<Long> categoryIdsDataSample)  {
    List<Long> categoryIds = categoriesData.stream().map(category -> category.getId()).collect(Collectors.toList());

    return categoryIds.containsAll(categoryIdsDataSample);
  }
}
