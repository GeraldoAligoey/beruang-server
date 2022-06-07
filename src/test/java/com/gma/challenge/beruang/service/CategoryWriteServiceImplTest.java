package com.gma.challenge.beruang.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import com.gma.challenge.beruang.data.CategoryResponseData;
import com.gma.challenge.beruang.exception.CategoryNotFoundException;
import com.gma.challenge.beruang.exception.IncompleteRequestDataException;
import com.gma.challenge.beruang.helper.CategoryHelper;
import com.gma.challenge.beruang.repo.CategoryRepository;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
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
  public void testUpdateCategory_validId_validFullRequestData() {
    CategoryResponseData categoryResponseData = SUT.updateCategory(VALID_ID, CategoryHelper.getValidFullUpdateCategoryRequestDataSample());
    assertNotNull(categoryResponseData);
    assertNotNull(categoryResponseData.getCategory());
    assertTrue(CategoryHelper.isUpdateCategoryResponseDataEqualsToSample(categoryResponseData));
  }

  @Test
  public void testUpdateCategory_validId_validNotFullRequestData() {
    CategoryResponseData categoryResponseData = SUT.updateCategory(VALID_ID, CategoryHelper.getValidNotFullUpdateCategoryRequestDataSample());
    assertNotNull(categoryResponseData);
    assertNotNull(categoryResponseData.getCategory());
    assertTrue(CategoryHelper.isUpdateCategoryResponseDataEqualsToNotFullSample(categoryResponseData));
  }

  @Test
  public void testUpdateCategory_validId_invalidRequestData() {
    assertThrows(IncompleteRequestDataException.class, () -> SUT.updateCategory(VALID_ID, CategoryHelper.getInvalidUpdateCategoryRequestDataSample()));
  }

  @Test
  public void testUpdateCategory_invalidId_validFullRequestData() {
    assertThrows(CategoryNotFoundException.class, () -> SUT.updateCategory(INVALID_ID, CategoryHelper.getValidFullUpdateCategoryRequestDataSample()));
  }

  @Test
  public void testUpdateCategory_invalidId_validNotFullRequestData() {
    assertThrows(CategoryNotFoundException.class, () -> SUT.updateCategory(INVALID_ID, CategoryHelper.getValidNotFullUpdateCategoryRequestDataSample()));
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
