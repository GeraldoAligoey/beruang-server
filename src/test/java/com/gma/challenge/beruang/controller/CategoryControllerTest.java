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
public class CategoryControllerTest {

  private static final long VALID_ID = 1l;
  private static final Long INVALID_ID = -1l;
  private static final Long VALID_LINKED_ID = 500l;
  private static final Long VALID_UNLINKED_ID = 502l;

  @Autowired
  private CategoryController SUT;

  @Test
  public void testCreateCategory_validNewCategoryRequestData() {
    ResponseEntity<CategoryResponseData> responseEntity = SUT.createCategory(CategoryHelper.getValidNewCategoryRequestDataSample());
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody().getCategory());
    assertTrue(CategoryHelper.isCategoryResponseDataEqualsToSample(responseEntity.getBody()));
  }
  
  @Test
  public void testCreateCategory_invalidNewCategoryRequestData() {
    assertThrows(IncompleteRequestDataException.class, () -> SUT.createCategory(CategoryHelper.getInvalidNewCategoryRequestDataSample()));
  }

  @Test
  @Sql("classpath:sql/testDeleteCategory.sql")
  public void testDeleteCategory_validId_linked() {
    ResponseEntity<Void> responseEntity = SUT.deleteCategory(VALID_LINKED_ID);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertThrows(CategoryNotFoundException.class, () -> SUT.findCategory(VALID_LINKED_ID));
  }

  @Test
  @Sql("classpath:sql/testDeleteCategory.sql")
  public void testDeleteCategory_validId_unlinked() {
    ResponseEntity<Void> responseEntity = SUT.deleteCategory(VALID_UNLINKED_ID);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertThrows(CategoryNotFoundException.class, () -> SUT.findCategory(VALID_UNLINKED_ID));
  }

  @Test
  public void testDeleteCategory_invalidId() {
    assertThrows(CategoryNotFoundException.class, () -> SUT.deleteCategory(INVALID_ID));
  }

  @Test
  public void testFindCategory_validId_recordExist() {
    ResponseEntity<CategoryResponseData> responseEntity = SUT.findCategory(VALID_ID);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody().getCategory());
  }

  @Test
  public void testFindCategory_validId_recordNotExist() {
    assertThrows(CategoryNotFoundException.class, () -> SUT.findCategory(100l));
  }

  @Test
  public void testFindCategory_invalidId() {
    assertThrows(CategoryNotFoundException.class, () -> SUT.findCategory(INVALID_ID));
  }

  @Test
  @Sql("classpath:sql/testFind_empty.sql")
  public void testFindCategories_empty() {
    ResponseEntity<CategoriesResponseData> responseEntity = SUT.findCategories();
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody().getCategories());
    assertTrue(responseEntity.getBody().getCategories().isEmpty());
  }

  @Test
  @Sql("classpath:sql/testFindCategories_singleItem.sql")
  public void testFindCategories_singleItem() {
    ResponseEntity<CategoriesResponseData> responseEntity = SUT.findCategories();
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody().getCategories());
    assertTrue(responseEntity.getBody().getCategories().size() == 1);
  }

  @Test
  @Sql("classpath:sql/testFindCategories_multipleItems.sql")
  public void testFindCategories_multipleItems() {
    ResponseEntity<CategoriesResponseData> responseEntity = SUT.findCategories();
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody().getCategories());
    assertTrue(responseEntity.getBody().getCategories().size() > 1);
  }

  @Test
  public void testUpdateCategory_validId_validRequestData() {
    ResponseEntity<CategoryResponseData> responseEntity = SUT.updateCategory(VALID_ID, CategoryHelper.getValidUpdateCategoryRequestDataSample());
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody().getCategory());
    assertTrue(CategoryHelper.isUpdateCategoryResponseDataEqualsToSample(responseEntity.getBody()));
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
}
