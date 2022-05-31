package com.gma.challenge.beruang.service;

import com.gma.challenge.beruang.data.CategoryResponseData;
import com.gma.challenge.beruang.data.NewCategoryRequestData;
import com.gma.challenge.beruang.data.UpdateCategoryRequestData;

public interface CategoryWriteService {

  public CategoryResponseData createCategory(NewCategoryRequestData newCategoryRequestData);

  public CategoryResponseData updateCategory(Long id, UpdateCategoryRequestData updateCategoryRequestData);

  public void deleteCategory(Long id);

}
