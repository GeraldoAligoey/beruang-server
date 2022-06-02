package com.gma.challenge.beruang.helper;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.gma.challenge.beruang.data.CategoryData;
import com.gma.challenge.beruang.data.CategoryResponseData;
import com.gma.challenge.beruang.data.NewCategoryRequestData;

public class CategoryHelper {

  /**
   *
   */
  private static final String CATEGORY_ICON = "fa-solid fa-books";
  /**
   *
   */
  private static final boolean CATEGORY_EXPENSE = true;
  /**
   *
   */
  private static final String CATEGORY_COLOR = "green";
  /**
   *
   */
  private static final String CATEGORY_NAME = "Hobby";

  public static NewCategoryRequestData getValidNewCategoryRequestDataSample() {
    NewCategoryRequestData requestData = new NewCategoryRequestData();
    requestData.setName(CATEGORY_NAME);
    requestData.setExpense(CATEGORY_EXPENSE);
    requestData.setColor(CATEGORY_COLOR);
    requestData.setIcon(CATEGORY_ICON);

    return requestData;
  }

  public static boolean isCategoryResponseDataEqualsToSample(CategoryResponseData responseData) {
    CategoryData category = responseData.getCategory();

    if (!category.getName().equals(CATEGORY_NAME)) {
      return false;
    }

    if (!category.getExpense().equals(CATEGORY_EXPENSE)) {
      return false;
    }

    if (!category.getColor().equals(CATEGORY_COLOR)) {
      return false;
    }

    if (!category.getIcon().equals(CATEGORY_ICON)) {
      return false;
    }

    return true;
  }
  
  public static NewCategoryRequestData getInvalidNewCategoryRequestDataSample() {
    NewCategoryRequestData requestData = new NewCategoryRequestData();
    requestData.setExpense(CATEGORY_EXPENSE);
    requestData.setColor(CATEGORY_COLOR);
    requestData.setIcon(CATEGORY_ICON);

    return requestData;
  }
}
