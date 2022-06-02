package com.gma.challenge.beruang.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.gma.challenge.beruang.data.CategoryResponseData;
import com.gma.challenge.beruang.exception.IncompleteRequestDataException;
import com.gma.challenge.beruang.helper.CategoryHelper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class CategoryWriteServiceImplTest {
  
  @Autowired
  private CategoryWriteServiceImpl SUT;

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

  public void testUpdateCategory_validId_validRequestData() {

  }

  public void testUpdateCategory_validId_invalidRequestData() {

  }

  public void testUpdateCategory_invalidId_validRequestData() {

  }

  public void testUpdateCategory_invalidId_invalidRequestData() {

  }

  public void testDeleteCategory_validId() {

  }

  public void testDeleteCategory_invalidId() {

  }
}
