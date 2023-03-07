package com.gma.challenge.beruang.service;

import com.gma.challenge.beruang.generated.dto.*;

public interface CategoryWriteService {

    public CategoryResponseData createCategory(NewCategoryRequestData newCategoryRequestData);

    public CategoryResponseData updateCategory(
            Long id, UpdateCategoryRequestData updateCategoryRequestData);

    public void deleteCategory(Long id);
}
