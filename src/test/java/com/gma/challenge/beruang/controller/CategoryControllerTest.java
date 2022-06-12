package com.gma.challenge.beruang.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.gma.challenge.beruang.data.CategoriesResponseData;
import com.gma.challenge.beruang.data.CategoryResponseData;
import com.gma.challenge.beruang.exception.CategoryNotFoundException;
import com.gma.challenge.beruang.exception.IncompleteRequestDataException;
import com.gma.challenge.beruang.helper.CategoryHelper;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class CategoryControllerTest implements ControllerTest {

  private static final long VALID_ID = 1l;
  private static final Long INVALID_ID = -1l;
  private static final Long VALID_LINKED_ID = 500l;
  private static final Long VALID_UNLINKED_ID = 502l;

  @Autowired
  private CategoryController SUT;

  @Test
  @Override
  public void testCreate_validNewRequestData() {
    ResponseEntity<CategoryResponseData> responseEntity = SUT
        .createCategory(CategoryHelper.getValidNewCategoryRequestDataSample());
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody().getCategory());
    assertTrue(CategoryHelper.isCategoryResponseDataEqualsToSample(responseEntity.getBody()));
  }

  @Test
  @Override
  public void testCreate_invalidEmptyNewRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.createCategory(CategoryHelper.getInvalidEmptyNewCategoryRequestDataSample()));
  }

  @Test
  @Override
  public void testCreate_invalidIncompleteNewRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.createCategory(CategoryHelper.getInvalidIncompleteNewCategoryRequestDataSample()));
  }

  @Test
  @Override
  public void testCreate_invalidNullNewRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.createCategory(CategoryHelper.getInvalidNullNewCategoryRequestDataSample()));
  }

  @Test
  @Override
  @Sql("classpath:sql/testDeleteCategory.sql")
  public void testDelete_validId_linked() {
    ResponseEntity<Void> responseEntity = SUT.deleteCategory(VALID_LINKED_ID);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertThrows(CategoryNotFoundException.class, () -> SUT.findCategory(VALID_LINKED_ID));
  }

  @Test
  @Override
  @Sql("classpath:sql/testDeleteCategory.sql")
  public void testDelete_validId_unlinked() {
    ResponseEntity<Void> responseEntity = SUT.deleteCategory(VALID_UNLINKED_ID);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertThrows(CategoryNotFoundException.class, () -> SUT.findCategory(VALID_UNLINKED_ID));
  }

  @Test
  @Override
  public void testDelete_invalidId() {
    assertThrows(CategoryNotFoundException.class, () -> SUT.deleteCategory(INVALID_ID));
  }

  @Test
  @Override
  public void testFindRecord_validId_recordExist() {
    ResponseEntity<CategoryResponseData> responseEntity = SUT.findCategory(VALID_ID);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody().getCategory());
  }

  @Test
  @Override
  public void testFindRecord_validId_recordNotExist() {
    assertThrows(CategoryNotFoundException.class, () -> SUT.findCategory(100l));
  }

  @Test
  @Override
  public void testFindRecord_invalidId() {
    assertThrows(CategoryNotFoundException.class, () -> SUT.findCategory(INVALID_ID));
  }

  @Test
  @Override
  @Sql("classpath:sql/testFind_empty.sql")
  public void testFindRecords_empty() {
    ResponseEntity<CategoriesResponseData> responseEntity = SUT.findCategories();
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody().getCategories());
    assertTrue(responseEntity.getBody().getCategories().isEmpty());
  }

  @Test
  @Override
  @Sql("classpath:sql/testFindCategories_single.sql")
  public void testFindRecords_single() {
    ResponseEntity<CategoriesResponseData> responseEntity = SUT.findCategories();
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody().getCategories());
    assertTrue(responseEntity.getBody().getCategories().size() == 1);
  }

  @Test
  @Override
  @Sql("classpath:sql/testFindCategories_multiple.sql")
  public void testFindRecords_multiple() {
    ResponseEntity<CategoriesResponseData> responseEntity = SUT.findCategories();
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody().getCategories());
    assertTrue(responseEntity.getBody().getCategories().size() > 1);
  }

  @Test
  @Override
  public void testUpdate_validId_validFullRequestData() {
    ResponseEntity<CategoryResponseData> responseEntity = SUT.updateCategory(VALID_ID,
        CategoryHelper.getValidFullUpdateCategoryRequestDataSample());
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody().getCategory());
    assertTrue(CategoryHelper.isUpdateCategoryResponseDataEqualsToSample(responseEntity.getBody()));
  }

  @Test
  @Override
  public void testUpdate_validId_validPartialRequestData() {
    ResponseEntity<CategoryResponseData> responseEntity = SUT.updateCategory(VALID_ID,
        CategoryHelper.getValidPartialUpdateCategoryRequestDataSample());
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody().getCategory());
    assertTrue(CategoryHelper.isUpdateCategoryResponseDataEqualsToPartialSample(responseEntity.getBody()));
  }

  @Test
  @Override
  public void testUpdate_validId_invalidNullRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.updateCategory(VALID_ID, CategoryHelper.getInvalidNullUpdateCategoryRequestDataSample()));
  }

  @Test
  @Override
  public void testUpdate_validId_invalidEmptyRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.updateCategory(VALID_ID, CategoryHelper.getInvalidEmptyUpdateCategoryRequestDataSample()));
  }

  @Test
  @Override
  public void testUpdate_invalidId_validFullRequestData() {
    assertThrows(CategoryNotFoundException.class,
        () -> SUT.updateCategory(INVALID_ID, CategoryHelper.getValidFullUpdateCategoryRequestDataSample()));
  }

  @Test
  @Override
  public void testUpdate_invalidId_invalidEmptyRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.updateCategory(INVALID_ID, CategoryHelper.getInvalidEmptyUpdateCategoryRequestDataSample()));
  }

  @Test
  @Override
  public void testUpdate_invalidId_validPartialRequestData() {
    assertThrows(CategoryNotFoundException.class,
        () -> SUT.updateCategory(INVALID_ID, CategoryHelper.getValidPartialUpdateCategoryRequestDataSample()));
  }

  @Override
  public void testUpdate_invalidId_invalidNullRequestData() {
    assertThrows(CategoryNotFoundException.class,
        () -> SUT.updateCategory(INVALID_ID, CategoryHelper.getInvalidNullUpdateCategoryRequestDataSample()));
    
  }
}
