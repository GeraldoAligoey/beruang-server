package com.gma.challenge.beruang.helper;

import java.util.Arrays;
import java.util.List;

import com.gma.challenge.beruang.data.CategoryData;
import com.gma.challenge.beruang.data.CategoryResponseData;
import com.gma.challenge.beruang.data.NewCategoryRequestData;
import com.gma.challenge.beruang.data.UpdateCategoryRequestData;

public class CategoryHelper {

  private static final String CATEGORY_ICON = "fa-solid fa-books";
  private static final boolean CATEGORY_EXPENSE = true;
  private static final String CATEGORY_COLOR = "green";
  private static final String CATEGORY_NAME = "Hobby";

  private static final String UPDATE_CATEGORY_ICON = "updated fa-solid fa-books";
  private static final boolean UPDATE_CATEGORY_EXPENSE = false;
  private static final String UPDATE_CATEGORY_COLOR = "updated green";
  private static final String UPDATE_CATEGORY_NAME = "Updated Hobby";

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

  public static UpdateCategoryRequestData getValidFullUpdateCategoryRequestDataSample() {
    UpdateCategoryRequestData requestData = new UpdateCategoryRequestData();
    requestData.setName(UPDATE_CATEGORY_NAME);
    requestData.setExpense(UPDATE_CATEGORY_EXPENSE);
    requestData.setColor(UPDATE_CATEGORY_COLOR);
    requestData.setIcon(UPDATE_CATEGORY_ICON);

    return requestData;
  }

  public static boolean isUpdateCategoryResponseDataEqualsToSample(CategoryResponseData responseData) {
    CategoryData category = responseData.getCategory();

    if (!category.getName().equals(UPDATE_CATEGORY_NAME)) {
      return false;
    }

    if (!category.getExpense().equals(UPDATE_CATEGORY_EXPENSE)) {
      return false;
    }

    if (!category.getColor().equals(UPDATE_CATEGORY_COLOR)) {
      return false;
    }

    if (!category.getIcon().equals(UPDATE_CATEGORY_ICON)) {
      return false;
    }

    return true;
  }

  public static UpdateCategoryRequestData getInvalidUpdateCategoryRequestDataSample() {
    UpdateCategoryRequestData requestData = new UpdateCategoryRequestData();
    return requestData;
  }

  public static UpdateCategoryRequestData getValidPartialUpdateCategoryRequestDataSample() {
    UpdateCategoryRequestData requestData = new UpdateCategoryRequestData();
    requestData.setExpense(UPDATE_CATEGORY_EXPENSE);
    requestData.setColor(UPDATE_CATEGORY_COLOR);
    return requestData;
  }

  public static boolean isUpdateCategoryResponseDataEqualsToPartialSample(
      CategoryResponseData categoryResponseData) {
    CategoryData category = categoryResponseData.getCategory();
    if (!category.getExpense().equals(UPDATE_CATEGORY_EXPENSE)) {
      return false;
    }

    if (!category.getColor().equals(UPDATE_CATEGORY_COLOR)) {
      return false;
    }

    return true;
  }

  public static List<Long> getCategoryIdsDataSample() {
    return Arrays.asList(Long.valueOf(1), Long.valueOf(2));
  }

  public static UpdateCategoryRequestData getNullUpdateCategoryRequestDataSample() {
    return null;
  }


}
