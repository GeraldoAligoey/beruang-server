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
public class CategoryWriteServiceImplTest implements WriteServiceTest {

  private static final long VALID_ID = 1l;
  private static final Long INVALID_ID = -1l;

  @Autowired
  private CategoryWriteServiceImpl SUT;

  @Autowired
  private CategoryRepository categoryRepository;

  @Test
  @Override
  public void testCreate_validRequestData() {
    CategoryResponseData categoryResponseData = SUT
        .createCategory(CategoryHelper.getValidNewCategoryRequestDataSample());
    assertNotNull(categoryResponseData);
    assertNotNull(categoryResponseData.getCategory());
    assertTrue(CategoryHelper.isCategoryResponseDataEqualsToSample(categoryResponseData));
  }

  @Test
  @Override
  public void testCreate_invalidRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.createCategory(CategoryHelper.getInvalidIncompleteNewCategoryRequestDataSample()));
  }

  @Test
  @Override
  public void testUpdate_validId_validFullRequestData() {
    CategoryResponseData categoryResponseData = SUT.updateCategory(VALID_ID,
        CategoryHelper.getValidFullUpdateCategoryRequestDataSample());
    assertNotNull(categoryResponseData);
    assertNotNull(categoryResponseData.getCategory());
    assertTrue(CategoryHelper.isUpdateCategoryResponseDataEqualsToSample(categoryResponseData));
  }

  @Test
  @Override
  public void testUpdate_validId_validPartialRequestData() {
    CategoryResponseData categoryResponseData = SUT.updateCategory(VALID_ID,
        CategoryHelper.getValidPartialUpdateCategoryRequestDataSample());
    assertNotNull(categoryResponseData);
    assertNotNull(categoryResponseData.getCategory());
    assertTrue(CategoryHelper.isUpdateCategoryResponseDataEqualsToPartialSample(categoryResponseData));
  }

  @Test
  @Override
  public void testUpdate_validId_invalidEmptyRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.updateCategory(VALID_ID, CategoryHelper.getInvalidEmptyUpdateCategoryRequestDataSample()));
  }

  @Test
  @Override
  public void testUpdate_validId_invalidNullRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.updateCategory(VALID_ID, CategoryHelper.getInvalidNullUpdateCategoryRequestDataSample()));
  }

  @Test
  @Override
  public void testUpdate_invalidId_validFullRequestData() {
    assertThrows(CategoryNotFoundException.class,
        () -> SUT.updateCategory(INVALID_ID, CategoryHelper.getValidFullUpdateCategoryRequestDataSample()));
  }

  @Test
  @Override
  public void testUpdate_invalidId_validPartialRequestData() {
    assertThrows(CategoryNotFoundException.class,
        () -> SUT.updateCategory(INVALID_ID, CategoryHelper.getValidPartialUpdateCategoryRequestDataSample()));
  }

  @Test
  @Override
  public void testUpdate_invalidId_invalidEmptyRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.updateCategory(INVALID_ID, CategoryHelper.getInvalidEmptyUpdateCategoryRequestDataSample()));
  }

  @Test
  @Override
  public void testUpdate_invalidId_invalidNullRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.updateCategory(INVALID_ID, CategoryHelper.getInvalidNullUpdateCategoryRequestDataSample()));
  }

  @Test
  @Override
  public void testDelete_validId() {
    SUT.deleteCategory(VALID_ID);
    assertNull(categoryRepository.findById(VALID_ID).orElse(null));
  }

  @Test
  @Override
  public void testDelete_invalidId() {
    assertThrows(CategoryNotFoundException.class, () -> SUT.deleteCategory(INVALID_ID));
  }
}
