package com.gma.challenge.beruang.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.gma.challenge.beruang.data.CategoryResponseData;
import com.gma.challenge.beruang.exception.CategoryNotFoundException;
import com.gma.challenge.beruang.exception.IncompleteRequestDataException;
import com.gma.challenge.beruang.helper.CategoryHelper;
import com.gma.challenge.beruang.repo.CategoryRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class CategoryWriteServiceImplTest {

  private static final long VALID_ID = 1l;
  private static final Long INVALID_ID = -1l;

  @Autowired
  private CategoryWriteServiceImpl SUT;

  @Autowired
  private CategoryRepository categoryRepository;

  @Test
  public void testCreateCategory_validRequestData() {
    CategoryResponseData categoryResponseData = SUT.createCategory(CategoryHelper.getValidNewCategoryRequestDataSample());
    assertNotNull(categoryResponseData);
    assertNotNull(categoryResponseData.getCategory());
    assertTrue(CategoryHelper.isCategoryResponseDataEqualsToSample(categoryResponseData));
  }

  @Test
  public void testCreateCategory_invalidRequestData() {
    assertThrows(IncompleteRequestDataException.class, () -> SUT.createCategory(CategoryHelper.getInvalidNewCategoryRequestDataSample()));
  }

  @Test
  public void testUpdateCategory_validId_validRequestData() {
    CategoryResponseData categoryResponseData = SUT.updateCategory(VALID_ID, CategoryHelper.getValidUpdateCategoryRequestDataSample());
    assertNotNull(categoryResponseData);
    assertNotNull(categoryResponseData.getCategory());
    assertTrue(CategoryHelper.isUpdateCategoryResponseDataEqualsToSample(categoryResponseData));
  }

  @Test
  public void testUpdateCategory_validId_invalidRequestData() {
    assertThrows(IncompleteRequestDataException.class, () -> SUT.updateCategory(VALID_ID, CategoryHelper.getInvalidUpdateCategoryRequestDataSample()));
  }

  @Test
  public void testUpdateCategory_invalidId_validRequestData() {
    assertThrows(CategoryNotFoundException.class, () -> SUT.updateCategory(INVALID_ID, CategoryHelper.getValidUpdateCategoryRequestDataSample()));
  }

  @Test
  public void testUpdateCategory_invalidId_invalidRequestData() {
    assertThrows(IncompleteRequestDataException.class, () -> SUT.updateCategory(INVALID_ID, CategoryHelper.getInvalidUpdateCategoryRequestDataSample()));
  }

  @Test
  public void testDeleteCategory_validId() {
    SUT.deleteCategory(VALID_ID);
    assertNull(categoryRepository.findById(VALID_ID).orElse(null));
  }

  @Test
  public void testDeleteCategory_invalidId() {
    assertThrows(CategoryNotFoundException.class, () -> SUT.deleteCategory(INVALID_ID));
  }
}
